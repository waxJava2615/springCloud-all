package com.starry.sky.infrastructure.authentication;

import com.starry.sky.common.exception.CustomizeAuthenticationException;
import com.starry.sky.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description 实现登录逻辑
 */
@Component
public class AdminLoginProvider implements AuthenticationProvider {




    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminLoginAuthenticationToken authenticationToken = (AdminLoginAuthenticationToken) authentication;

        String password = (String)authenticationToken.getCredentials();

        // 查询用户，如果存在该用户则认证通过，否则认证失败
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null)
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_NOT_USER.getMessage(),
                    ResultCode.AUTHENTICATION_NOT_USER.getCode());

        // 函数构造一个认证通过的Token，包含了用户信息和用户权限
        // 放入  判断密码是否相等
        boolean matches = passwordEncoder.matches(passwordEncoder.encode(password), userDetails.getPassword());
        if (!matches){
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_ERROR_PASSWORD.getMessage(),
                    ResultCode.AUTHENTICATION_ERROR_PASSWORD.getCode());
        }

        AdminLoginAuthenticationToken authenticationResult = new AdminLoginAuthenticationToken(userDetails, "此处填写密码",
                userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return AdminLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
