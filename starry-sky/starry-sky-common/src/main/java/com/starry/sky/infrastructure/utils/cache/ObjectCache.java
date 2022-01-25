package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: 普通缓存
 * @date 2021-10-09
 */
@Slf4j
@Component
public class ObjectCache extends AbstractParamsCacheKey {

    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.OBJECT_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.OBJECT_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.OBJECT_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.OBJECT_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.OBJECT_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.OBJECT_LIST));
    }

    public void removeKey(String ...keys){
        this.getRedissonClient().getKeys().delete(keys);
    }

}
