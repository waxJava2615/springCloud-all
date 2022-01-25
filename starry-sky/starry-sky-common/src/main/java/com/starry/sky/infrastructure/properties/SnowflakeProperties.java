package com.starry.sky.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wax
 * @description: TODO
 * @date 2022-01-05
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "starry.sky.snowflake")
public class SnowflakeProperties {

    private int workerId;

    private int dataCenterId;

}
