package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;

import java.util.List;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminMenuDORepository{
    
    
    List<SysAdminMenuDO> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO);

    SysAdminMenuDO findByMenuId(SysAdminMenuDTO sysAdminMenuDTO);

    int update(SysAdminMenuDO sysAdminMenuDO);

    int delete(List<Long> ids);

    int add(SysAdminMenuDO sysAdminMenuDO);

}