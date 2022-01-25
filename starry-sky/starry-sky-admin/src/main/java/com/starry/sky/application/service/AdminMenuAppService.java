package com.starry.sky.application.service;

import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.PagerDTO;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;

import java.util.List;

/**
 * @author wax
 * @description: 菜单
 * @date 2021-12-17
 */
public interface AdminMenuAppService {


    /**
     * 后台获取菜单列表
     * @param sysAdminMenuDTO
     * @return
     */
    PagerDTO<AdminMenuDTO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO);


    /**
     * 根据ID获取菜单信息
     * @param sysAdminMenuDTO
     * @return
     */
    AdminMenuDTO loadMenuSelect(SysAdminMenuDTO sysAdminMenuDTO);

    /**
     * 加载菜单树结构
     */
    List<AdminMenuDTO> loadMenuTree();

    /**
     * 根据ID删除菜单
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
    int pushMenu(SysAdminMenuDTO sysAdminMenuDTO);
}
