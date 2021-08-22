package com.starry.sky.infrastructure.config;

import com.starry.sky.infrastructure.authentication.NoOpPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wax
 * @description: springsecurity配置
 * @date 2021-08-20
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    // 自定义登录实现 AbstractAuthenticationProcessingFilter
    // AbstractAuthenticationToken
    //
    //
    // AuthenticationSuccessHandler
    // AuthenticationFailureHandler



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


                .and()
                .authorizeRequests()
                .antMatchers("/inset").permitAll()
                .anyRequest().authenticated()


//                .and()
//                .formLogin()
//                .successHandler()
//                .failureHandler()
                .and()
                .csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new NoOpPasswordEncoder();
    }
}
