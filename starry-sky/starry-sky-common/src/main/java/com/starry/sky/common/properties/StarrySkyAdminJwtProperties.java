package com.starry.sky.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wax
 * @description: 读取配置文件
 * @date 2021-08-20
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class StarrySkyAdminJwtProperties {

    /**
     * 发行者
     */
    private String issuer;


    /**
     * 向的用户
     */
    private String subject;


    /**
     * 接收该JWT者
     */
    private String issuedAt;


    /**
     * 秘钥
     */
    private String secret;

    /**
     * 什么时候过期，这里是一个Unix时间戳
     */
    private Long expiration;
}
