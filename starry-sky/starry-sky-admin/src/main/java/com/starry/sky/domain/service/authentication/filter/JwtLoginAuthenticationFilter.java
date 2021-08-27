package com.starry.sky.domain.service.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.starry.sky.common.exception.CustomizeRuntimeException;
import com.starry.sky.common.utils.ResultData;
import com.starry.sky.domain.repository.SysAdminUserRepository;
import com.starry.sky.domain.service.authentication.provider.AdminLoginAuthenticationToken;
import com.starry.sky.infrastructure.config.authentication.JwtGenerateProcess;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author wax
 * @description: JWT过滤器  参考BasicAuthenticationFilter
 * @date 2021-08-23
 */
@Slf4j
public class JwtLoginAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_PREFIX = "Bearer ";

    private JwtGenerateProcess jwtGenerateProcess;

    private ObjectMapper objectMapper;

    private UserDetailsService userDetailsService;

    private SysAdminUserRepository sysAdminUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(header) || !header.startsWith(AUTHENTICATION_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 校验token
        final String token = header.split(" ")[1].trim();
        try {
            if (!jwtGenerateProcess.validate(token)) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (CustomizeRuntimeException e) {
            ResultData resultData = ResultData.customizeResult(e.getCode(), e.getMessage());
            ResultData.printJson(request, response, objectMapper.writeValueAsString(resultData));
            return;
        }
        Claims claims = jwtGenerateProcess.parseJWT(token);
        String userName = String.valueOf(claims.get(JwtGenerateProcess.CLAIMS_KEY_NAME_USER_NAME));
        // 查询用户信息构建userDetail
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        AdminLoginAuthenticationToken adminLoginAuthenticationToken = new AdminLoginAuthenticationToken(userDetails,
                null, null, userDetails == null ? Lists.newArrayList() : userDetails.getAuthorities());
        adminLoginAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(adminLoginAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setJwtGenerateProcess(JwtGenerateProcess jwtGenerateProcess) {
        this.jwtGenerateProcess = jwtGenerateProcess;
    }

    public void setSysAdminUserRepository(SysAdminUserRepository sysAdminUserRepository) {
        this.sysAdminUserRepository = sysAdminUserRepository;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
