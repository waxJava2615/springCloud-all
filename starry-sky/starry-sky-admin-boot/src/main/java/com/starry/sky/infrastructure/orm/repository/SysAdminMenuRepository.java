package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;

import java.util.List;

/**
 * @author wax
 * @description: 菜单仓储
 * @date 2021-08-20
 */
public interface SysAdminMenuRepository<T extends BaseEntity> extends BaseRepository<SysAdminMenu> {


    List<SysAdminMenu> findByMenuIdList(List<Long> listPermissionId);


    SysAdminMenu findByMenuId(long menuId);

    /**
     * 根据ID更新menu
     *
     * @param sysAdminMenu
     * @return
     */
    int updateByMenuId(SysAdminMenu sysAdminMenu);
}