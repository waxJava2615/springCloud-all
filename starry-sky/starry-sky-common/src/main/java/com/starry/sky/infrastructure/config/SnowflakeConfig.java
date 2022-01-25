package com.starry.sky.infrastructure.config;

import com.starry.sky.infrastructure.properties.SnowflakeProperties;
import com.starry.sky.infrastructure.utils.SnowflakeGenerate;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wax
 * @description: 雪花算法生成ID
 * @date 2022-01-05
 */

@Slf4j
@Setter
@Configuration
@ConditionalOnProperty(prefix = "starry.sky.snowflake",name = "enable",havingValue = "true")
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowflakeConfig {

    @Autowired
    SnowflakeProperties snowflakeProperties;

    @Bean
    public SnowflakeGenerate snowflakeGenerate(){
        return new SnowflakeGenerate(snowflakeProperties.getWorkerId(),snowflakeProperties.getDataCenterId());
    }




}
