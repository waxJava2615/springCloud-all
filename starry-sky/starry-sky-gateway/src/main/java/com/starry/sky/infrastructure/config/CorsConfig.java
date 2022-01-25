package com.starry.sky.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-29
 */
@Configuration
public class CorsConfig {

    /**
     * 该访问配置跨域访问执行
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        //cors跨域配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);//是否允许携带cookie
        corsConfiguration.addAllowedOrigin("*"); //允许跨域访问的域名，可填写具体域名，*代表允许所有访问
        corsConfiguration.addAllowedMethod("*");//允许访问类型：get  post 等，*代表所有类型
        corsConfiguration.addAllowedHeader("*");

        //配置源对象
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        //cors 过滤器对象  注意！CorsWebFilter不要导错包
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
}
