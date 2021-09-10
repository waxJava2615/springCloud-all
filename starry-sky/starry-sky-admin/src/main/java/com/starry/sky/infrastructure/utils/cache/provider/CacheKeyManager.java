package com.starry.sky.infrastructure.utils.cache.provider;

import com.starry.sky.infrastructure.param.SysAdminParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wax
 * @description: 当对象发送变化是调用此方法获取所有的KEY
 * @date 2021-09-09
 */
@Component
public class CacheKeyManager {

    @Autowired
    private List<? extends AbstractParamsCacheKey> cacheKeyList;

    /**
     * 获取支持改clazz参数的CacheKey
     * @param clazz
     * @return
     */
    public CacheKey getCacheKey(Class clazz) {
        CacheKey cacheKeyProvider = null;
        for (Object aClass : cacheKeyList) {
            CacheKey cacheKey = (CacheKey) aClass;
            if (cacheKey.support(clazz)){
                cacheKeyProvider = cacheKey;
            }
        }
        return cacheKeyProvider;
    }


    /**
     * 通过参数 获取所有KEY 生成的方式
     * @param sysAdminParam
     * @return
     */
    public List<String> getCacheKeyList(SysAdminParam sysAdminParam) {
        CacheKey cacheKey = getCacheKey(sysAdminParam.getClass());
        return cacheKey.getCacheKeyList(sysAdminParam);
    }



}
