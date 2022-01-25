package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.generate.CacheTableConstans;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: 用户缓存keY生成
 * @date 2021-09-09
 */
@Component
public class SysAdminUserCache extends AbstractParamsCacheKey {

    @Autowired
    CacheKeyManager cacheKeyManager;

    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_USER},type = CacheKeyConstants.SYS_TYPE_DEFAULT)
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @CustomGenerateCacheKey(useTable = {CacheTableConstans.TABLE_SYS_ADMIN_USER},type = CacheKeyConstants.SYS_TYPE_LIST)
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST));
    }


    public String findByUserNameCacheKey(SysAdminUserDTO sysAdminUserDTO){
        return String.format("%s-findByUserName:%s",this.getClass().getSimpleName(),sysAdminUserDTO.getUserName());
    }

    public String findByUserIdCacheKey(SysAdminUserDTO sysAdminUserDTO){
        return String.format("%s-findByUserId:%s",this.getClass().getSimpleName(),sysAdminUserDTO.getId());
    }


    public SysAdminUser findByUserId(SysAdminUserDTO sysAdminUserDTO){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserDTO);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserId(SysAdminUserDTO sysAdminUserDTO,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserDTO);
        cacheKeyManager.pushObjManager(generateObjManager(),sysAdminUser.getId().toString(),cacheKey);
        super.set(cacheKey, sysAdminUser, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }


    public SysAdminUser findByUserName(SysAdminUserDTO sysAdminUserDTO){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserDTO);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserName(SysAdminUserDTO sysAdminUserDTO,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserDTO);
        cacheKeyManager.pushObjManager(generateObjManager(),sysAdminUser.getId().toString(),cacheKey);
        super.set(cacheKey, sysAdminUser, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }

}
