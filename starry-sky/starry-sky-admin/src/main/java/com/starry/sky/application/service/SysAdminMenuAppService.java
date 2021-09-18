package com.starry.sky.application.service;

import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;

/**
 * @author wax
 * @description: 换取菜单
 * @date 2021-09-13
 */
public interface SysAdminMenuAppService {


    /**
     * 更新菜单
     * @param sysAdminMenuDTO
     * @return
     */
    boolean updateMenu(SysAdminMenuDTO sysAdminMenuDTO);

}
