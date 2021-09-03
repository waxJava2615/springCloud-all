package com.starry.sky.common.cache;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-02
 */
public class RedisCache {


    public static void main(String[] args) {
        Config config = new Config();
//        properties.useClusterServers()
//                .setScanInterval(2000)
//                .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
//                .addNodeAddress("redis://10.0.29.205:6379");

        RedissonClient redisson = Redisson.create(config);
    }

}
