package com.starry.sky.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.starry.sky.infrastructure.filter.CustomRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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


//    //授权码服务
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }


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


    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 属性为Null的不进行序列化，只对pojo起作用，对map和list不起作用
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // json进行换行缩进等操作
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // json不进行换行缩进等操作  默认就是不进行操作，写了这行和没写的效果一样
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        // json是否允许属性名没有引号 ，默认是false
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //json是否允许属性名为单引号 ，默认是false
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 遇到未知属性是否抛出异常 ，默认是抛出异常的
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 当实体类没有setter方法时，序列化不报错，返回一个空对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 所有的字母小写，下划线作为名字之间分隔符，例如 snake_case
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // 所有名字（包括第一个字符）都以大写字母开头，后跟小写字母，没有分隔符，例如 UpperCamelCase
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        // 第一个单词以小写字母开头，后续每个单词都是大写字母开头，没有分隔符，例如 lowerCamelCase
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
//        // 所有的字母小写，没有分隔符，例如 lowercase
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
//        // “Lisp” 风格，采用小写字母、连字符作为分隔符，例如“lower-case” 或 “first-name”
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        return objectMapper;
    }

}
