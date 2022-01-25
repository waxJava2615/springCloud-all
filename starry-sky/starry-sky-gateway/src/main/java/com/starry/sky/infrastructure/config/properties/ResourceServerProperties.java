package com.starry.sky.infrastructure.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "starry.sky.resource")
public class ResourceServerProperties implements Serializable {

     @Value("#{'${ignoreUrls:}'.split(',')}")
//    @Value("${ignoreUrls}")
    private List<String> ignoreUrls;


}
