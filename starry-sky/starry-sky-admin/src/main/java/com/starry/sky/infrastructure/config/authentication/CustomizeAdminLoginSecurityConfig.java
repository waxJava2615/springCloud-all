package com.starry.sky.infrastructure.config.authentication;

import com.starry.sky.domain.service.authentication.AdminLoginProvider;
import com.starry.sky.infrastructure.filter.AdminLoginAuthenticationProcessingFilter;
import com.starry.sky.infrastructure.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: admin登录配置
 * @date 2021-08-24
 */
@Component
public class CustomizeAdminLoginSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,
        HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtGenerateProcess jwtGenerateProcess;

    @Override
    public void configure(HttpSecurity http) throws Exception {
// 1. 初始化 AdminLoginAuthenticationProcessingFilter
        AdminLoginAuthenticationProcessingFilter adminLoginAuthenticationProcessingFilter =
                new AdminLoginAuthenticationProcessingFilter();
        adminLoginAuthenticationProcessingFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        adminLoginAuthenticationProcessingFilter.setAuthenticationSuccessHandler(successHandler);
        adminLoginAuthenticationProcessingFilter.setAuthenticationFailureHandler(failureHandler);

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setJwtGenerateProcess(jwtGenerateProcess);

        // 2. 初始化 AdminLoginProvider
        AdminLoginProvider adminLoginProvider = new AdminLoginProvider();
        adminLoginProvider.setUserDetailsService(userDetailService);
        adminLoginProvider.setPasswordEncoder(passwordEncoder);
        // 3. 将设置完毕的 Filter 与 Provider 添加到配置中，将自定义的 Filter 加到 UsernamePasswordAuthenticationFilter 之前
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(adminLoginProvider).addFilterBefore(adminLoginAuthenticationProcessingFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
