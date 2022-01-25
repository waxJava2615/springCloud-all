package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminPermissionMenuRelationDO;
import com.starry.sky.infrastructure.dto.SysAdminPermissionMenuRelationDTO;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限菜单仓储
 */
public interface SysAdminPermissionMenuRelationDORepository {
    
    
    
    
    List<SysAdminPermissionMenuRelationDO> findByPermissionId(SysAdminPermissionMenuRelationDTO sysAdminPermissionMenuRelationDTO);
    
    
}
