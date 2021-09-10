package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.infrastructure.param.SysAdminRoleParam;

import java.util.List;

/**
  * @description: 查询角色ORM
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminRoleDORepository{


  /**
   * 分页获取 SysAdminRoleDO
   * @param sysAdminRoleParam
   * @return
   */
  List<SysAdminRoleDO> findList(SysAdminRoleParam sysAdminRoleParam);

  /**
   * 根据id列表获取 SysAdminRoleDO
   * @param sysAdminRoleParam
   * @return
   */
  List<SysAdminRoleDO> findByIds(SysAdminRoleParam sysAdminRoleParam);
  
  
  
}