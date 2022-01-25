package com.starry.sky.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-12-17
 */
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        converters.add(stringHttpMessageConverter);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    /**
     * Override this method to configure cross origin requests processing.
     *
     * @param registry
     * @see CorsRegistry
     * @since 4.2
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET","POST", "PUT", "DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Header1", "Header2");
    }

}
