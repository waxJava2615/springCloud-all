package com.starry.sky.infrastructure.config.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.domain.repository.SysAdminUserRepository;
import com.starry.sky.domain.service.authentication.filter.AdminLoginAuthenticationProcessingFilter;
import com.starry.sky.domain.service.authentication.filter.JwtLoginAuthenticationFilter;
import com.starry.sky.domain.service.authentication.provider.AdminLoginProvider;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SysAdminUserRepository sysAdminUserRepository;



//    方案一
//    @Autowired
//    private AdminFilterInvocationSecurityMetadataSource adminFilterInvocationSecurityMetadataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
// 1. 初始化 AdminLoginAuthenticationProcessingFilter
        AdminLoginAuthenticationProcessingFilter adminLoginAuthenticationProcessingFilter =
                new AdminLoginAuthenticationProcessingFilter();
        adminLoginAuthenticationProcessingFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        adminLoginAuthenticationProcessingFilter.setAuthenticationSuccessHandler(successHandler);
        adminLoginAuthenticationProcessingFilter.setAuthenticationFailureHandler(failureHandler);

        JwtLoginAuthenticationFilter jwtAuthenticationFilter = new JwtLoginAuthenticationFilter();
        jwtAuthenticationFilter.setJwtGenerateProcess(jwtGenerateProcess);
        jwtAuthenticationFilter.setObjectMapper(objectMapper);
        jwtAuthenticationFilter.setUserDetailsService(userDetailService);
        jwtAuthenticationFilter.setSysAdminUserRepository(sysAdminUserRepository);

        // 2. 初始化 AdminLoginProvider
        AdminLoginProvider adminLoginProvider = new AdminLoginProvider();
        adminLoginProvider.setUserDetailsService(userDetailService);
        adminLoginProvider.setPasswordEncoder(passwordEncoder);
        // 3. 将设置完毕的 Filter 与 Provider 添加到配置中，将自定义的 Filter 加到 UsernamePasswordAuthenticationFilter 之前
        http.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authenticationProvider(adminLoginProvider).addFilterBefore(adminLoginAuthenticationProcessingFilter,
                UsernamePasswordAuthenticationFilter.class);

        // 权限认证方案一
//        AdminFilterSecurityInterceptor adminFilterSecurityInterceptor = new AdminFilterSecurityInterceptor();
//        adminFilterSecurityInterceptor.setAdminFilterInvocationSecurityMetadataSource(adminFilterInvocationSecurityMetadataSource);
//        http.addFilterAfter(adminFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
