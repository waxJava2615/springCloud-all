package com.starry.sky.infrastructure.config;

import com.starry.sky.infrastructure.filter.CustomRequestFilter;
import com.starry.sky.infrastructure.security.support.CustomAccessDeniedHandler;
import com.starry.sky.infrastructure.security.support.CustomAuthenticationEntryPoint;
import com.starry.sky.infrastructure.security.support.provider.captcha.CustomUsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

/**
 * @author wax
 * @description: 安全配置
 * @date 2021-10-25
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认的登陆端点
     */
    public static final String DEFAULT_LOGIN_URL = "/login";

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomRequestFilter customRequestFilter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;


    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
        ;
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider(){
        return new CustomUsernamePasswordAuthenticationProvider(passwordEncoder(),userDetailsService);
    }


    /**
     * Description: 用默认的登陆界面生成过滤器生成默认的登陆界面 /login.<br>
     * Details: 的 如果使用了自定义{@link AuthenticationEntryPoint}, {@link DefaultLoginPageGeneratingFilter} 就不会被配置, 所以这里需要手动配置.
     *
     * @see DefaultLoginPageGeneratingFilter
     * @see org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer
     */
    private DefaultLoginPageGeneratingFilter defaultLoginPageGeneratingFilter() {
        final DefaultLoginPageGeneratingFilter defaultLoginPageGeneratingFilter = new DefaultLoginPageGeneratingFilter(new UsernamePasswordAuthenticationFilter());
        defaultLoginPageGeneratingFilter.setAuthenticationUrl(DEFAULT_LOGIN_URL);
        return defaultLoginPageGeneratingFilter;
    }
}
