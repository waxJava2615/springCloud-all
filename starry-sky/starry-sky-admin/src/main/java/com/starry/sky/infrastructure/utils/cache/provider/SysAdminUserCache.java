package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.common.constant.StarrySkyAdminConstants;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyGenerate;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-03
 */

@Service
public class SysAdminUserCache implements CacheProvider<SysAdminUser> {
    
    
    @Autowired
    RedissonClient redissonClient;
    
    @Autowired
    @Qualifier(value = "sysAdminUserCacheKeyGenerate")
    CacheKeyGenerate cacheKeyGenerate;
    
    
    
    @Override
    public void set(Map<String,Object> params,SysAdminUser baseEntity) {
        String key = generateCacheKey(params);
        if (!StringUtils.isEmpty(key)){
            redissonClient.getBucket(key).setIfExists(baseEntity,
                    StarrySkyAdminConstants.SYS_DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
        }
        
    }
    
    @Override
    public SysAdminUser get(Map<String,Object> params) {
        String key = generateCacheKey(params);
        if (StringUtils.isEmpty(key)){
            return null;
        }
        return (SysAdminUser) redissonClient.getBucket(key).get();
    }
    
    
    @Override
    public void setMap(Map<String, Object> params, SysAdminUser baseEntity) {
        String key = cacheKeyGenerate.generateObj();
        RMap<Object, Object> map = redissonClient.getMap(key);
        String keyMap = generateCacheKey(params);
        if (!StringUtils.isEmpty(keyMap)){
            map.put(keyMap,baseEntity);
        }
    }
    
    @Override
    public SysAdminUser getMap(Map<String, Object> params) {
        String key = cacheKeyGenerate.generateObj();
        RMap<Object, Object> map = redissonClient.getMap(key);
        String keyMap = generateCacheKey(params);
        if (StringUtils.isEmpty(keyMap)){
            return null;
        }
        return (SysAdminUser) map.get(keyMap);
    }
    
    @Override
    public void setList(Map<String, Object> params, List<SysAdminUser> list) {
        String key = cacheKeyGenerate.generateList();
        String keyMap = generateCacheKey(params);
        if (!StringUtils.isEmpty(keyMap)){
            redissonClient.getMap(key).put(keyMap,list);
        }
    }
    
    
    /**
     * 生成缓存key
     *
     * @param params
     * @return
     */
    public String generateCacheKey(Map<String, Object> params) {
        if (params == null){
            return null;
        }
        List<Entry<String, Object>> list = new ArrayList<>(params.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getKey));
        StringBuilder sb = new StringBuilder();
        list.forEach(p -> {
            sb.append(p.getKey()).append(EQ).append(p.getValue()).append(AND);
        });
        return sb.toString();
    }
}
