package com.starry.sky.infrastructure.config;

import com.starry.sky.common.properties.StarrySkyRedisProperties;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: redis配置
 * @date 2021-09-02
 */
@Slf4j
@Setter
@Component
@EnableConfigurationProperties(StarrySkyRedisProperties.class)
public class RedisConfig {

    @Autowired
    private StarrySkyRedisProperties starrySkyRedisProperties;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress(starrySkyRedisProperties.getSingleAddress());
        config.setLockWatchdogTimeout(starrySkyRedisProperties.getLockWatchdogTimeout());
        return Redisson.create(config);
    }


    @Bean
    public RedissonLockTemplate redissonLockTemplate(){
        return new RedissonLockTemplate(redissonClient());
    }

}
