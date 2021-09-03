package com.starry.sky.infrastructure.cache.provider;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-03
 */

@Service
public class SysAdminUserCache extends AbstractCacheProvider {

    @Autowired
    RedissonClient redissonClient;

    public SysAdminUserCache(){
        super.setRedissonClient(redissonClient);
    }

    /**
     * 生成缓存key
     *
     * @param params
     * @return
     */
    @Override
    String generateCacheKey(Map<String, Object> params) {
        List<Entry<String, Object>> list = new ArrayList<>(params.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getKey));
        StringBuilder sb = new StringBuilder();
        list.forEach(p -> {
            sb.append(p.getKey()).append(EQ).append(p.getValue()).append(AND);
        });
        return sb.toString();
    }
}
