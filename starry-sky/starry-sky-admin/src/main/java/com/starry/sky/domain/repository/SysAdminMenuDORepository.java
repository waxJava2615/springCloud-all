package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.Pager;

import java.util.List;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminMenuDORepository{
    
    
    List<SysAdminMenuDO> findByMenuIdList(SysAdminMenuDTO sysAdminMenuDTO);

    SysAdminMenuDO findByMenuId(SysAdminMenuDTO sysAdminMenuDTO);

    SysAdminMenuDO findByOnlyKey(SysAdminMenuDTO sysAdminMenuDTO);

    int update(SysAdminMenuDO sysAdminMenuDO);


    /**
     * 获取菜单列表
     * @param sysAdminMenuDTO
     * @return
     */
    List<SysAdminMenuDO> findListByHide(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 后台根据（id 或者 父ID 或者 隐藏显示） 分页查询
     * @param sysAdminMenuDTO
     * @return
     */
    Pager<SysAdminMenuDO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 根据ID删除菜单
     * @param sysAdminMenuDTO
     */
    int removeMenu(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 新增菜单
     * @param sysAdminMenuDO
     * @return
     */
    int cusSave(SysAdminMenuDO sysAdminMenuDO);
}