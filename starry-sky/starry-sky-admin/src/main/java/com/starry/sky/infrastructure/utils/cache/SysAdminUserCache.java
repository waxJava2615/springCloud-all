package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.param.SysAdminUserParam;
import com.starry.sky.infrastructure.utils.cache.generate.CacheJoinConstans;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wax
 * @description: 用户缓存keY生成
 * @date 2021-09-09
 */
@Component
public class SysAdminUserCache extends AbstractParamsCacheKey {



    @CustomGenerateCacheKey
    public String findByUserNameCacheKey(SysAdminUserParam sysAdminUserParam){
        return String.format("%s:%s",generateDefault(),sysAdminUserParam.getUserName());
    }

    @CustomGenerateCacheKey
    public String findByUserIdCacheKey(SysAdminUserParam sysAdminUserParam){
        return String.format("%s:%s",generateDefault(),sysAdminUserParam.getId());
    }


    @Override
    public boolean support(Class clazz) {
        return SysAdminUserParam.class.isAssignableFrom(clazz);
    }

    @CustomGenerateCacheKey
    @Override
    public String generateList() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_LIST));
    }

    @Override
    public String generateObj() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_OBJ),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_OBJ),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_OBJ));
    }

    @Override
    public String generateDefault() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_DEFAULT));
    }

    @CustomGenerateCacheKey(joinTable = {CacheJoinConstans.TABLE_SYS_ADMIN_USER, CacheJoinConstans.TABLE_SYS_ADMIN_ROLE,
            CacheJoinConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION} )
    public String userRoleRelationJoinKey(String ...tables){
        return generateJoinKey(tables);
    }

    public String findUserRoleByUserIdKey(SysAdminUserParam sysAdminUserParam){
        return String.format("findUserRoleByUserId:%s",sysAdminUserParam.getId());
    }


    public List findUserRoleByUserId(SysAdminUserParam sysAdminUserParam){
        String generateTableJoin = userRoleRelationJoinKey(CacheJoinConstans.TABLE_SYS_ADMIN_USER, CacheJoinConstans.TABLE_SYS_ADMIN_ROLE,
                CacheJoinConstans.TABLE_SYS_ADMIN_USER_ROLE_RELATION);
        String userRoleByUserIdKey = findUserRoleByUserIdKey(sysAdminUserParam);
        return super.getJoinForList(generateTableJoin,userRoleByUserIdKey,List.class);
    }


    public SysAdminUser findByUserId(SysAdminUserParam sysAdminUserParam){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserParam);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserId(SysAdminUserParam sysAdminUserParam,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserIdCacheKey(sysAdminUserParam);
        super.set(cacheKey, sysAdminUser);
    }


    public SysAdminUser findByUserName(SysAdminUserParam sysAdminUserParam){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserParam);
        return super.get(cacheKey, SysAdminUser.class);
    }

    public void findByUserName(SysAdminUserParam sysAdminUserParam,SysAdminUser sysAdminUser){
        String cacheKey = this.findByUserNameCacheKey(sysAdminUserParam);
        super.set(cacheKey, sysAdminUser);
    }

}
