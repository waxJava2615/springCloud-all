package com.starry.sky.domain.service.authentication.provider;

import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.exception.CustomizeAuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description 实现登录逻辑
 */
public class AdminLoginProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;


    //UsernameNotFoundException 用户找不到
    //BadCredentialsException 坏的凭据
    //AccountStatusException 用户状态异常它包含如下子类
    //AccountExpiredException 账户过期
    //LockedException 账户锁定
    //DisabledException 账户不可用
    //CredentialsExpiredException 证书过期
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminLoginAuthenticationToken authenticationToken = (AdminLoginAuthenticationToken) authentication;

        String password = (String) authenticationToken.getCredentials();

        // 查询用户，如果存在该用户则认证通过，否则认证失败
        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        } catch (UsernameNotFoundException e) {
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_NOT_USER.getCode(),ResultCode.AUTHENTICATION_NOT_USER.getMessage());
        }

        // 函数构造一个认证通过的Token，包含了用户信息和用户权限
        // 放入  判断密码是否相等
        boolean matches = passwordEncoder.matches(passwordEncoder.encode(password), userDetails.getPassword());
        if (!matches) {
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_ERROR_PASSWORD.getCode(),ResultCode.AUTHENTICATION_ERROR_PASSWORD.getMessage());
        }

        AdminLoginAuthenticationToken adminLoginAuthenticationToken = new AdminLoginAuthenticationToken(userDetails, null,
                null,userDetails.getAuthorities());
        adminLoginAuthenticationToken.setDetails(authenticationToken.getDetails());
        SecurityContextHolder.getContext().setAuthentication(adminLoginAuthenticationToken);
        return adminLoginAuthenticationToken;

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return AdminLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
