package com.starry.sky.infrastructure.cache.provider;

import java.util.Map;

/**
 * @author wax
 * @description: 不同模块缓存提供者
 * @date 2021-09-03
 */
public interface CacheProvider {

    void set(Map<String,Object> params, Object baseEntity);

    Object get(Map<String,Object> params);


//    boolean supports(Class<?> cacheProvider);


}
