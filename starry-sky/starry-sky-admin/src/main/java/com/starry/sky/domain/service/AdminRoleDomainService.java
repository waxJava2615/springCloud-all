package com.starry.sky.domain.service;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;

import java.util.List;

/**
 * @author wax
 * @description: 角色类
 * @date 2021-11-10
 */
public interface AdminRoleDomainService {


    /**
     * 根据角色ID获取对应的资源菜单
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO);

    /**
     * 根据角色ID获取对应的资源操作
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findRolePermissionOperation(SysAdminRoleDTO sysAdminRoleDTO);


    /**
     * 根据操作ID获取权限
     * @param currentSysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findRoleByOperationId(SysAdminRoleDTO currentSysAdminRoleDTO);

    /**
     * 根据角色ID列表 获取角色数组
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO);


    /**
     * 根据权限名称查询权限
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findByNames(SysAdminRoleDTO sysAdminRoleDTO);


}
