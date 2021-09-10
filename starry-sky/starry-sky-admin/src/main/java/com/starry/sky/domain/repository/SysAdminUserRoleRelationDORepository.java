package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.infrastructure.param.SysAdminUserRoleParam;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 用户角色仓储
 */
public interface SysAdminUserRoleRelationDORepository{
    
    
    List<SysAdminUserRoleRelationDO> findByUserId(SysAdminUserRoleParam sysAdminUserRoleParam);
    
    
}
