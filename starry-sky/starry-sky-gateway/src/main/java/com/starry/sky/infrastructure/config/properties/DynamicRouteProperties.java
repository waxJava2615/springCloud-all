package com.starry.sky.infrastructure.config.properties;

/**
 * @author wax
 * @description: 动态路由属性
 * @date 2021-10-08
 */

import com.starry.sky.infrastructure.config.DynamicRouteConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "starry.sky.route")
@ConditionalOnBean(DynamicRouteConfiguration.class)
public class DynamicRouteProperties {

    /**
     * nacos 配置管理  dataId
     */
    private String routeDataId;
    /**
     * nacos 配置管理 group
     */
    private String projectGroup;
    /**
     * nacos 服务地址
     */
    private String address;


    private String namespace;

}
