package com.starry.sky.infrastructure.utils.cache.provider;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 不同模块缓存提供者
 * @date 2021-09-03
 */
public interface CacheProvider<T> {
    
    final String EQ = "=";
    
    final String AND = "&";

    void set(Map<String,Object> params, T baseEntity);

    T get(Map<String,Object> params);
    
    void setMap(Map<String,Object> params, T baseEntity);
    
    T getMap(Map<String, Object> params);
    
    void setList(Map<String,Object> params, List<T> list);
    
    


}
