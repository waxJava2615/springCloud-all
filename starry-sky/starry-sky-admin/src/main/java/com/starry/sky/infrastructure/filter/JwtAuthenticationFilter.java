package com.starry.sky.infrastructure.filter;

import com.google.common.collect.Lists;
import com.starry.sky.domain.service.authentication.AdminLoginAuthenticationToken;
import com.starry.sky.infrastructure.config.authentication.JwtGenerateProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_PREFIX = "Bearer ";


    private JwtGenerateProcess JwtGenerateProcess;

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
        if (!JwtGenerateProcess.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 查询用户信息构建userDetail
        UserDetails userDetails = null;
        AdminLoginAuthenticationToken adminLoginAuthenticationToken = new AdminLoginAuthenticationToken(userDetails,
                null,null, userDetails == null ? Lists.newArrayList() : userDetails.getAuthorities());

        adminLoginAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // TODO 修改为放入redis
        SecurityContextHolder.getContext().setAuthentication(adminLoginAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    public void setJwtGenerateProcess(com.starry.sky.infrastructure.config.authentication.JwtGenerateProcess jwtGenerateProcess) {
        JwtGenerateProcess = jwtGenerateProcess;
    }
}
