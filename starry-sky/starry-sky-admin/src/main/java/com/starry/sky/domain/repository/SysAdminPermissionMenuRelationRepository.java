package com.starry.sky.domain.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限菜单仓储
 */
public interface SysAdminPermissionMenuRelationRepository<T extends BaseEntity> extends BaseRepository<SysAdminPermissionMenuRelation> {
    
    
    
    
    List<SysAdminPermissionMenuRelation> findByPermissionId(List<Long> listPermissionId);
    
    
}
