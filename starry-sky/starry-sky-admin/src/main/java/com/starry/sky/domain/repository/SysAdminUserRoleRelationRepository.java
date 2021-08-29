package com.starry.sky.domain.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 用户角色仓储
 */
public interface SysAdminUserRoleRelationRepository<T extends BaseEntity> extends BaseRepository<SysAdminUserRoleRelation> {
    
    
    List<SysAdminUserRoleRelation> findByUserId(Long id);
    
    
}
