package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminRolePermissionRelationDO;
import com.starry.sky.infrastructure.param.SysAdminRolePermissionRelationParam;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 角色权限关联仓储
 */
public interface SysAdminRolePermissionRelationDORepository {


    List<SysAdminRolePermissionRelationDO> findByRoleId(SysAdminRolePermissionRelationParam sysAdminRolePermissionRelationParam);

}
