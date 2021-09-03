package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminMenuDO;

import java.util.List;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminMenuDORepository{
    
    
    List<SysAdminMenuDO> findByMenuId(List<Long> listPermissionId);
}