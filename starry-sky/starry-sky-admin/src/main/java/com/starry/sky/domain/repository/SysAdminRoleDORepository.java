package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminRoleDO;

import java.util.List;

/**
  * @description: 查询角色ORM
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminRoleDORepository{


  /**
   * 获取所有角色
   * @return
   */
  List<SysAdminRoleDO> findAll();
  
  List<SysAdminRoleDO> findByIds(List<Long> listRoleId);
  
  
  
}