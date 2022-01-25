package com.starry.sky.infrastructure.config;

import com.starry.sky.infrastructure.filter.CustomRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author wax
 * @description: bean 注册
 * @date 2021-10-09
 */
@Configuration
public class BeanRegsitoryConfig {

//    @Autowired
//    private DataSource dataSource;


    @Bean
    public Filter customRequestFilter(){
        return new CustomRequestFilter();
    }

    @Bean
    public FilterRegistrationBean customRequestFilterRegistration() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加过滤器
        registration.setFilter(customRequestFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/**");
        registration.setOrder(0);
        return registration;
    }

}
