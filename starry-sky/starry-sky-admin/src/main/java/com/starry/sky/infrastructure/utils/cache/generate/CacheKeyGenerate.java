package com.starry.sky.infrastructure.utils.cache.generate;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/4
 * @description 缓存Key生成器
 */
public interface CacheKeyGenerate {


    /**
     * 缓存管理器    管理当前对象的所有KEY
     * @return
     */
    String generateListManager();

    /**
     * 缓存管理器   当前表连接的所有KEY
     * @return
     */
    String generateJoinListManager();


}
