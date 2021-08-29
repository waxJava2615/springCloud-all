package com.starry.sky.domain.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 角色权限关联仓储
 */
public interface SysAdminRolePermissionRelationRepository<T extends BaseEntity> extends BaseRepository<SysAdminRolePermissionRelation> {
    
    
    List<SysAdminRolePermissionRelation> findByRoleId(List<Long> listRoleId);
    
}
