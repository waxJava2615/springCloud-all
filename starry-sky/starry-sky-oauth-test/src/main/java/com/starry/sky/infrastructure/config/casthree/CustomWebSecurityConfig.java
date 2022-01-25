package com.starry.sky.infrastructure.config.casthree;

import com.starry.sky.infrastructure.oauth.support.user.CustomUsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

/**
 * @author wax
 * @description: 自定义安全配置
 * @date 2021-10-22
 */
//@Configuration
//@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认的登陆端点
     */
    public static final String DEFAULT_LOGIN_URL = "/login";

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;


    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new CustomUsernamePasswordAuthenticationProvider(passwordEncoder(),userDetailsService));
    }


//    /**
//     * 配置默认的密码授权模式的ProviderManager，否则 密码授权 client认证将不会通过。
//     * @return
//     */
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();

        http.formLogin()
                .and().addFilter(defaultLoginPageGeneratingFilter());
        // ~ 禁用 Authorization: Basic xxx
        http.httpBasic().disable();

        http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**");
        http.authorizeRequests()

                .anyRequest().authenticated();
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
