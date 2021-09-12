package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.annotation.CustomGenerateCacheKey;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
import com.starry.sky.infrastructure.param.SysAdminPermissionOperationRelationParam;
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
 * @description 权限操作缓存
 */
@Component
public class SysAdminPermissionOperationRelationCache extends AbstractParamsCacheKey {
    
    @CustomGenerateCacheKey
    @Override
    public String generateList() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_LIST));
    }
    
    @Override
    public String generateObj() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_OBJ),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_OBJ),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_OBJ));
    }
    
    @Override
    public String generateDefault() {
        return String.format("starry-sky-admin-%s-%s-%s",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_DEFAULT));
    }
    
    /**
     * 是否支持改类型参数
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean support(Class clazz) {
        return SysAdminPermissionOperationRelationParam.class.isAssignableFrom(clazz);
    }
    
    
    private String findByPermissionIdKey(SysAdminPermissionOperationRelationParam permissionOperationRelationParam) {
        return String.format("findByPermissionId:%s",
                permissionOperationRelationParam.getListOtherId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminPermissionOperationRelation> findByPermissionId(SysAdminPermissionOperationRelationParam permissionOperationRelationParam) {
        String cacheKey = generateList();
        String mapKey = findByPermissionIdKey(permissionOperationRelationParam);
        return super.getMapForList(cacheKey, mapKey, SysAdminPermissionOperationRelation.class);
    }
    
    
    public void findByPermissionId(SysAdminPermissionOperationRelationParam permissionOperationRelationParam,
                                   List<SysAdminPermissionOperationRelation> list) {
        String cacheKey = generateList();
        String mapKey = findByPermissionIdKey(permissionOperationRelationParam);
        super.setMapForList(cacheKey,mapKey,list);
    }
}
