package com.starry.sky.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author wax
 * @description: JSON处理配置
 * @date 2021-11-02
 */
@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        // 属性为Null的不进行序列化，只对pojo起作用，对map和list不起作用
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // json进行换行缩进等操作
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
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



//    @Bean("jackson2ObjectMapperBuilderCustomizer")
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jacksonObjectMapperBuilder -> {
//                jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
//                jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
//                jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
//                jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
//        };
//    }

}
