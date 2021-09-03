package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminPermissionOperationRelationDO;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限操作仓储
 */
public interface SysAdminPermissionOperationRelationDORepository {
    
    
    List<SysAdminPermissionOperationRelationDO> findByPermissionId(List<Long> listPermissionId);
}
