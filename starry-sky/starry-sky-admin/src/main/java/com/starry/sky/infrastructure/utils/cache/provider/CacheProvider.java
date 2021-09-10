package com.starry.sky.infrastructure.utils.cache.provider;

import java.util.List;

/**
 * @author wax
 * @description: 缓存基础类
 * @date 2021-09-09
 */
public interface CacheProvider {

    /**
     * 一个简单的SET
     * @param cacheKey
     * @param object
     */
    public void set(String cacheKey, Object object);

    /**
     * 一个简单的get
     * @param cacheKey
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T get(String cacheKey, Class<T> tClass);

    /**
     * 缓存单个对象
     * @param cacheKey
     * @param mapKey
     * @param object
     */
    void setMap(String cacheKey,String mapKey, Object object);

    /**
     * 获取单个对象
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getMap(String cacheKey,String mapKey,Class<T> tClass);


    /**
     * 缓存当前对象列表
     * @param cacheKey
     * @param mapKey
     * @param list
     */
    void setMapForList(String cacheKey,String mapKey, List list);

    /**
     * 获取当前对象列表
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> getMapForList(String cacheKey, String mapKey, Class<T> tClass);


    /**
     * 设置连表查询缓存
     * @param cacheKey
     * @param mapKey
     * @param list
     */
    void setJoinForList(String cacheKey,String mapKey, List list);

    /**
     * 获取连表查询结果
     * @param cacheKey
     * @param mapKey
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> getJoinForList(String cacheKey, String mapKey, Class<T> tClass);


}
