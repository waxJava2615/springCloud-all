package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;

import java.util.List;

/**
 * @author wax
 * @description: 查询角色ORM
 * @date 2021-08-20
 */
public interface SysAdminRoleDORepository {


    /**
     * 分页获取 SysAdminRoleDO
     *
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findList(SysAdminRoleDTO sysAdminRoleDTO);

    /**
     * 根据id列表获取 SysAdminRoleDO
     *
     * @param sysAdminRoleDTO
     * @return
     */
    List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO);


    /**
     * 获取角色对应的菜单
     *
     * @param sysAdminRoleDTO # listRoleId pageNum pageSize hide
     * @return
     */
    List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO);


}