package com.starry.sky.infrastructure.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import com.starry.sky.infrastructure.config.properties.DynamicRouteProperties;
import com.starry.sky.infrastructure.dto.RouteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @author wax
 * @description: 动态路由配置
 * @date 2021-10-08
 */
@Slf4j
@Configuration
public class DynamicRouteConfiguration {

    private DynamicRouteProperties dynamicRouteProperties;
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher applicationEventPublisher;

    public DynamicRouteConfiguration(DynamicRouteProperties dynamicRouteProperties,
                                     RouteDefinitionWriter routeDefinitionWriter, ApplicationEventPublisher applicationEventPublisher) {
        this.dynamicRouteProperties = dynamicRouteProperties;
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    private void init() {
        routeNacosListener();
    }

    private void routeNacosListener() {
        try {

            Properties properties = new Properties();
            properties.put("serverAddr",dynamicRouteProperties.getAddress());
            properties.put("namespace",dynamicRouteProperties.getNamespace());
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfigAndSignListener(
                    dynamicRouteProperties.getRouteDataId(),
                    dynamicRouteProperties.getProjectGroup(),
                    5000,
                    new AbstractListener() {
                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            updateConfig(configInfo);
                        }
                    });
            updateConfig(content);
        } catch (NacosException e) {
            log.error("nacos 获取动态路由配置和监听异常", e);
        }
    }

    private void updateConfig(String content) {
        log.info("nacos 动态路由更新： {}", content);
        try {
            getRouteDefinitions(content).forEach(routeDefinition -> {
                log.info("动态路由配置: {}", routeDefinition);
                routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
                routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
                applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            });
        } catch (Exception e) {
            log.error("更新动态路由配置异常: ", e);
        }
    }

    private List<RouteDefinition> getRouteDefinitions(String content) {
        // 解析yaml配置
        Yaml yaml = new Yaml();
        RouteDTO routeDTO = yaml.loadAs(content, RouteDTO.class);
        return routeDTO.getRoutes();
    }
}
