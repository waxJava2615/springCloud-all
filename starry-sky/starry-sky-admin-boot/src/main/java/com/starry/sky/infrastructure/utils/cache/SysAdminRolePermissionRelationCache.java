package com.starry.sky.infrastructure.utils.cache;

import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminRolePermissionRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;
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


    /**
     * 缓存管理器    管理当前对象的单个KEY
     *
     * @return
     */
    @Override
    public String generateObjManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_DEFAULT));
    }

    /**
     * 缓存管理器    管理当前对象的所有KEY
     *
     * @return
     */
    @Override
    public String generateListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_LIST));
    }

    private String findByRoleIdKey(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO) {
        return String.format("%s-findByRoleId:%s", this.getClass().getSimpleName(),
                sysAdminRolePermissionRelationDTO.getListRoleId().stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    public List<SysAdminRolePermissionRelation> findByRoleId(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO) {
        String cacheKey = findByRoleIdKey(sysAdminRolePermissionRelationDTO);
        return super.getList(cacheKey, SysAdminRolePermissionRelation.class);
    }

    public void findByRoleId(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO,
                             List<SysAdminRolePermissionRelation> list) {
        String cacheKey = findByRoleIdKey(sysAdminRolePermissionRelationDTO);
        super.setList(cacheKey, list, CommonConstants.SYS_DEFAULT_CACHE_TIME);
    }
}
