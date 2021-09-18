package com.starry.sky.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author wax
 * @description: redis config
 * @date 2021-09-02
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties(prefix = "starry.sky.redis")
public class StarrySkyRedisProperties {

    /**
     * 包含端口
     */
    private String singleAddress;


    /**
     * 看门狗时长   默认是30秒
     */
    private long lockWatchdogTimeout;


}
