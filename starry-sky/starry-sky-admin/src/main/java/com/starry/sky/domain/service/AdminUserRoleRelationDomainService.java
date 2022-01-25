package com.starry.sky.domain.service;

import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.infrastructure.dto.SysAdminUserRoleDTO;

import java.util.List;

/**
 * @author wax
 * @description: 用户角色关联类
 * @date 2021-11-10
 */
public interface AdminUserRoleRelationDomainService {

    /**
     * 根据用户ID获取角色ID集合
     * @param sysAdminUserRoleDTO
     * @return
     */
    List<SysAdminUserRoleRelationDO> loadRoleByUserId(SysAdminUserRoleDTO sysAdminUserRoleDTO);
}
