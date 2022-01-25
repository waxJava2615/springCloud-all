package com.starry.sky.infrastructure.config.casttwo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-21
 */

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomUserDetailService customUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }

//    /**
//     * 用户名密码认证授权提供者
//     *
//     * @return
//     */
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        // 是否隐藏用户不存在异常，默认:true-隐藏；false-抛出异常
//        provider.setHideUserNotFoundExceptions(false);
//        provider.setUserDetailsService(customUserDetailService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

}
