package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;
import com.starry.sky.infrastructure.param.SysAdminUserRoleParam;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wax
 * @description: 用户角色关联缓存
 * @date 2021-09-09
 */
@Component
public class SysAdminUserRoleRelationCache extends AbstractParamsCacheKey {


    /**
     * 当前对象list缓存
     * @return
     */
    @Override
    @CustomGenerateCacheKey
    public String generateList() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_LIST));
    }

    @Override
    public String generateObj() {
        return null;
    }


    @Override
    public String generateDefault() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_USER_ROLE_RELATION_DEFAULT));
    }

    @Override
    public boolean support(Class clazz) {
        return SysAdminUserRoleParam.class.isAssignableFrom(clazz);
    }

    public String findByUserIdKey(SysAdminUserRoleParam sysAdminUserRoleParam){
        return String.format("findByUserId-%s",sysAdminUserRoleParam.getUserId());
    }

    public List<SysAdminUserRoleRelation> findByUserId(SysAdminUserRoleParam sysAdminUserRoleParam){
        String cacheKey = generateList();
        String mapKey = findByUserIdKey(sysAdminUserRoleParam);
        return super.getMapForList(cacheKey,mapKey,SysAdminUserRoleRelation.class);
    }

    public void findByUserId(SysAdminUserRoleParam sysAdminUserRoleParam,List<SysAdminUserRoleRelation> list){
        String cacheKey = generateList();
        String mapKey = findByUserIdKey(sysAdminUserRoleParam);
        super.setMapForList(cacheKey,mapKey,list);
    }
}
