package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyGenerate;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-03
 */
public abstract class AbstractCacheProvider implements CacheProvider<Object> {

    @Autowired
    RedissonClient redissonClient;
    
    @Autowired
    CacheKeyGenerate cacheKeyGenerate;


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
    
    
    @Override
    public void setMap(Map<String, Object> params, Object baseEntity) {
        String key = cacheKeyGenerate.generateObj();
        RMap<Object, Object> map = redissonClient.getMap(key);
        String keyMap = generateCacheKey(params);
        map.put(keyMap,baseEntity);
    }
    
    @Override
    public Object getMap(Map<String, Object> params) {
        String key = cacheKeyGenerate.generateObj();
        RMap<Object, Object> map = redissonClient.getMap(key);
        String keyMap = generateCacheKey(params);
        return map.get(keyMap);
    }
    
    @Override
    public void setList(Map<String,Object> params, List<Object> list) {
    
    }
    
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    
    public void setCacheKeyGenerate(CacheKeyGenerate cacheKeyGenerate) {
        this.cacheKeyGenerate = cacheKeyGenerate;
    }
}
