package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;
import com.starry.sky.infrastructure.param.SysAdminRolePermissionRelationParam;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyConstants;
import com.starry.sky.infrastructure.utils.cache.generate.CacheKeyEnum;
import com.starry.sky.infrastructure.utils.cache.provider.AbstractParamsCacheKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 角色权限关联缓存
 */
@Component
public class SysAdminRolePermissionRelationCache extends AbstractParamsCacheKey {
    @Override
    public String generateList() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST));
    }
    
    @Override
    public String generateObj() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_OBJ),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_OBJ),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_OBJ));
    }
    
    @Override
    public String generateDefault() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT));
    }
    
    /**
     * 是否支持改类型参数
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean support(Class clazz) {
        return SysAdminRolePermissionRelationParam.class.isAssignableFrom(clazz);
    }
    
    private String findByRoleIdKey(SysAdminRolePermissionRelationParam sysAdminRolePermissionRelationParam){
        return String.format("findByRoleId:%s",
                sysAdminRolePermissionRelationParam.getListRoleId().stream().map(Object::toString
        ).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminRolePermissionRelation> findByRoleId(SysAdminRolePermissionRelationParam sysAdminRolePermissionRelationParam) {
        String cacheKey = this.generateList();
        String mapKey = findByRoleIdKey(sysAdminRolePermissionRelationParam);
        return super.getMapForList(cacheKey, mapKey, SysAdminRolePermissionRelation.class);
    }
    
    public void findByRoleId(SysAdminRolePermissionRelationParam sysAdminRolePermissionRelationParam,
                             List<SysAdminRolePermissionRelation> listSysAdminRolePermissionRelation) {
    }
}
