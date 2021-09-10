package com.starry.sky.infrastructure.config;

import com.starry.sky.infrastructure.filter.CustomRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wax
 * @description: web 配置类
 * @date 2021-09-06
 */
@Configuration
public class MvcConfig{


    @Bean
    public FilterRegistrationBean customRequestFilterRegistration() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加过滤器
        registration.setFilter(new CustomRequestFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        registration.setOrder(Integer.MAX_VALUE-10);
        return registration;
    }


}
