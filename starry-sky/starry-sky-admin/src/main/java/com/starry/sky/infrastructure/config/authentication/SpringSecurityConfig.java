package com.starry.sky.infrastructure.config.authentication;

import com.starry.sky.domain.repository.*;
import com.starry.sky.domain.service.authentication.NoOpPasswordEncoder;
import com.starry.sky.domain.service.authentication.handler.CustomAccessDeniedHandler;
import com.starry.sky.domain.service.authentication.handler.EntryPointUnauthorizedHandler;
import com.starry.sky.domain.service.authorization.casetwo.AdminSecurityMetadataSource;
import com.starry.sky.domain.service.authorization.casetwo.CustomAccessDecisionVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

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
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 身份验证失败
     */
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Autowired
    private SysAdminRoleDORepository sysAdminRoleRepository;

    @Autowired
    private SysAdminMenuDORepository sysAdminMenuRepository;

    @Autowired
    private SysAdminOperationDORepository sysAdminOperationRepository;

    @Autowired
    private SysAdminRolePermissionRelationDORepository sysAdminRolePermissionRelationRepository;

    @Autowired
    private SysAdminPermissionMenuRelationDORepository sysAdminPermissionMenuRelationRepository;

    @Autowired
    private SysAdminPermissionOperationRelationDORepository sysAdminPermissionOperationRelationRepository;



//    方案一   使用方案一  需要去 AdminFilterInvocationSecurityMetadataSource customAccessDecisionManager 放开@compent
//    @Autowired
//    private AdminFilterInvocationSecurityMetadataSource adminFilterInvocationSecurityMetadataSource;
//    @Autowired
//    private CustomAccessDecisionManager customAccessDecisionManager;


    // 自定义登录实现 AbstractAuthenticationProcessingFilter
    // AbstractAuthenticationToken
    // AuthenticationSuccessHandler
    // AuthenticationFailureHandler

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 每多一种登录  多配置一个config   有APPLY引入即可
        http.apply(customizeLoginSecurityConfig)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(
                        // 方案一
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                                // 方案一
//                                object.setSecurityMetadataSource(adminFilterInvocationSecurityMetadataSource);
//                                object.setAccessDecisionManager(customAccessDecisionManager);
//                                return object;
                                // 方案二
                                FilterInvocationSecurityMetadataSource securityMetadataSource = new AdminSecurityMetadataSource(
                                        object.getSecurityMetadataSource(), sysAdminRoleRepository,
                                        sysAdminMenuRepository, sysAdminOperationRepository, sysAdminRolePermissionRelationRepository, sysAdminPermissionMenuRelationRepository, sysAdminPermissionOperationRelationRepository);
                                object.setSecurityMetadataSource(securityMetadataSource);
                                return object;
                            }
                        }
                )
                // 方案二
                .accessDecisionManager(accessDecisionManager())
//                .antMatchers("/inset").hasAuthority("user")
//                .antMatchers("/admin/login").permitAll()
//                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(entryPointUnauthorizedHandler)
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


    @Bean
    public AccessDecisionVoter CustomAccessDecisionVoter() {
        AccessDecisionVoter autoMatchVoter = new CustomAccessDecisionVoter();
        return autoMatchVoter;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        decisionVoters.add(CustomAccessDecisionVoter());
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        decisionVoters.add(webExpressionVoter);
        AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
        return accessDecisionManager;
    }



}
