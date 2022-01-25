package com.starry.sky.domain.service;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.Pager;

import java.util.List;

/**
 * @author wax
 * @description: 菜单
 * @date 2021-11-15
 */
public interface AdminMenuDomainService {


    /**
     * 获取菜单
     * @param sysAdminMenuDTO
     * @return
     */
    List<SysAdminMenuDO> getMenu(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 后台根据（id 或者 父ID 或者 隐藏显示） 分页查询
     * @param sysAdminMenuDTO
     * @return
     */
    Pager<SysAdminMenuDO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 根据ID获取菜单详情
     * @param sysAdminMenuDTO
     * @return
     */
    SysAdminMenuDO loadMenuById(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 根据ID 删除菜单
     * @param sysAdminMenuDTO
     */
    int removeMenu(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 根据ID修改菜单
     * @param sysAdminMenuDTO
     */
    int editMenu(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 添加菜单
     * @param sysAdminMenuDTO
     */
    int  pushMenu(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 根据ID获取菜单 并获取父节点对象
     * @return
     */
    SysAdminMenuDO loadMenuSelect(SysAdminMenuDTO sysAdminMenuDTO);

}
