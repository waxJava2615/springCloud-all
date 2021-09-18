package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

/**
 * @author wax
 * @description: 用户缓存keY生成
 * @date 2021-09-09
 */
@Component
public class SysAdminUserCache extends AbstractParamsCacheKey {


    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST));
    }

    /**
     * 缓存管理器   当前表连接的所有KEY
     *
     * @return
     */
    @Override
    public String generateJoinListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_JOIN_TABLE),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_JOIN_TABLE),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_JOIN_TABLE));
    }



    public String findByUserNameCacheKey(SysAdminUserDTO sysAdminUserDTO){
        return String.format("findByUserName:%s",sysAdminUserDTO.getUserName());
    }

    public String findByUserIdCacheKey(SysAdminUserDTO sysAdminUserDTO){
        return String.format("findByUserId:%s",sysAdminUserDTO.getId());
    }


    public SysAdminUser findByUserId(SysAdminUserDTO sysAdminUserDTO){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserDTO);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserId(SysAdminUserDTO sysAdminUserDTO,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserDTO);
        super.set(cacheKey, sysAdminUser);
    }


    public SysAdminUser findByUserName(SysAdminUserDTO sysAdminUserDTO){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserDTO);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserName(SysAdminUserDTO sysAdminUserDTO,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserDTO);
        super.set(cacheKey, sysAdminUser);
    }

}
