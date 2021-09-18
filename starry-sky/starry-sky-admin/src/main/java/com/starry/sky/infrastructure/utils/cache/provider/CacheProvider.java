package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.infrastructure.orm.po.BaseEntity;

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
     * 缓存list
     * @param cacheKey
     * @param listEntity
     */
    public void setList(String cacheKey,List<? extends BaseEntity> listEntity);


    /**
     * 获取list
     * @param cacheKey
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String cacheKey, Class<T> tClass);




}
