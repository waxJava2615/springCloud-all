package com.starry.sky.infrastructure.utils.cache.generate;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/4
 * @description 缓存Key生成器
 */
public interface CacheKeyGenerate {
    
    
    String generateList();
    
    
    String generateObj();
    
    
    String generateDefault();


}
