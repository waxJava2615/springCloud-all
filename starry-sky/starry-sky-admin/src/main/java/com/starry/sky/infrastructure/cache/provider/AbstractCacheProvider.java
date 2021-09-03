package com.starry.sky.infrastructure.cache.provider;

import org.redisson.api.RedissonClient;

import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-03
 */
public abstract class AbstractCacheProvider implements CacheProvider {

    private RedissonClient redissonClient;


    final String EQ = "=";

    final String AND = "&";

    /**
     * 生成缓存key
     * @param params
     * @return
     */
    abstract String generateCacheKey(Map<String,Object> params);


    @Override
    public void set(Map<String,Object> params,Object baseEntity) {
        String key = generateCacheKey(params);
        redissonClient.getBucket(key).set(baseEntity);
    }

    @Override
    public Object get(Map<String,Object> params) {
        String key = generateCacheKey(params);
        return redissonClient.getBucket(key).get();
    }


    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
