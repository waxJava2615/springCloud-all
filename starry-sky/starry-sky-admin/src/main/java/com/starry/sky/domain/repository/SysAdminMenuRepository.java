package com.starry.sky.domain.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;

import java.util.List;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminMenuRepository<T extends BaseEntity> extends BaseRepository<SysAdminMenu> {
    
    
    List<SysAdminMenu> findByMenuId(List<Long> listPermissionId);
}