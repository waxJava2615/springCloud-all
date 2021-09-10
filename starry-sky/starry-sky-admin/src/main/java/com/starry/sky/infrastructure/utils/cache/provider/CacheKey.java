package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.infrastructure.param.SysAdminParam;

import java.util.List;

/**
 * @author wax
 * @description: 缓存KEY的支持类型
 * @date 2021-09-09
 */
public interface CacheKey {

    /**
     * 获取所有的key
     * @param sysAdminParam
     * @return
     * @throws Exception
     */
    public List<String> getCacheKeyList(SysAdminParam sysAdminParam);

    /**
     * 是否支持改类型参数
     * @param clazz
     * @return
     */
    public boolean support(Class clazz);

}
