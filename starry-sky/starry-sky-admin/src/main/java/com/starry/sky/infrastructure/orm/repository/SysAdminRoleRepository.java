package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;

import java.util.List;

/**
  * @description: 查询角色ORM
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminRoleRepository<t extends BaseEntity> extends BaseRepository<SysAdminRole> {


  /**
   * 获取所有角色
   * @return
   */
  List<SysAdminRole> findAll();
  
  List<SysAdminRole> findByIds(List<Long> listRoleId);
  
  
  
}