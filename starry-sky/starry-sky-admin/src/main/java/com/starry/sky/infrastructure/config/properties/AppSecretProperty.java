package com.starry.sky.infrastructure.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wax
 * @description: id 和 secret 配置
 * @date 2021-11-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "starry.sky.admin.app-secret")
public class AppSecretProperty {

    private String clientId;

    private String clientSecret;

    private String grantType;

}
