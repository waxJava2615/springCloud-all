package com.starry.sky.infrastructure.config.authentication;

import com.starry.sky.domain.service.authentication.CustomizeAccessDeniedHandler;
import com.starry.sky.domain.service.authentication.EntryPointUnauthorizedHandler;
import com.starry.sky.domain.service.authentication.NoOpPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author wax
 * @description: springsecurity配置
 * @date 2021-08-20
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Autowired
    private CustomizeAdminLoginSecurityConfig customizeLoginSecurityConfig;

    @Autowired
    private UserDetailsService userDetailService;


    /**
     * 自定义请求拒绝异常
     */
    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    /**
     * 身份验证失败
     */
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    // 自定义登录实现 AbstractAuthenticationProcessingFilter
    // AbstractAuthenticationToken
    // AuthenticationSuccessHandler
    // AuthenticationFailureHandler

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        AdminLoginAuthenticationProcessingFilter adminLoginAuthenticationProcessingFilter = new AdminLoginAuthenticationProcessingFilter();
//        adminLoginAuthenticationProcessingFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        adminLoginAuthenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        adminLoginAuthenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        // 2. 初始化 AdminLoginProvider
//        AdminLoginProvider adminLoginProvider = new AdminLoginProvider();
//        adminLoginProvider.setUserDetailsService(userDetailService);
//        http.authenticationProvider(adminLoginProvider).addFilterBefore(adminLoginAuthenticationProcessingFilter,
//                UsernamePasswordAuthenticationFilter.class);
//        http.apply(customizeLoginSecurityConfig)
//                .and()
        // 以上不分可以抽离出去单独一个配置  每多一种登录  多配置一个config   有APPLY引入即可
        http.apply(customizeLoginSecurityConfig)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/inset").hasAuthority("user")
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling().accessDeniedHandler(customizeAccessDeniedHandler).authenticationEntryPoint(entryPointUnauthorizedHandler)
                .and()

                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)

                .and()
                .csrf().disable();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new NoOpPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
