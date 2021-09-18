package com.starry.sky.infrastructure.utils.cache;

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

    /**
     * 缓存管理器   当前表连接的所有KEY
     *
     * @return
     */
    @Override
    public String generateJoinListManager() {
        return String.format("starry-sky-%s-%s-%s-manager",
                CacheKeyEnum.getPrefixByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE),
                CacheKeyEnum.getGroupByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE),
                CacheKeyEnum.getTypeByCode(CacheKeyConstants.SYS_ADMIN_ROLE_PERMISSION_RELATION_JOIN_TABLE));
    }

    private String findByRoleIdKey(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO){
        return String.format("findByRoleId:%s",
                sysAdminRolePermissionRelationDTO.getListRoleId().stream().map(Object::toString
        ).collect(Collectors.joining(",")));
    }
    
    public List<SysAdminRolePermissionRelation> findByRoleId(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO) {
        String cacheKey = findByRoleIdKey(sysAdminRolePermissionRelationDTO);
        return super.getList(cacheKey, SysAdminRolePermissionRelation.class);
    }
    
    public void findByRoleId(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO,
                             List<SysAdminRolePermissionRelation> list) {
        String cacheKey = findByRoleIdKey(sysAdminRolePermissionRelationDTO);
        super.setList(cacheKey, list);
    }
}
