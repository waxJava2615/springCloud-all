package com.starry.sky.application.service;

import com.starry.sky.application.dto.AdminUserDTO;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;

/**
 * @author wax
 * @description: 用户信息查询
 * @date 2021-11-10
 */
public interface AdminUserAppService {


    /**
     * 获取用户信息  包括权限
     * @param sysAdminUserDTO
     * @return
     */
    AdminUserDTO loadByUserName(SysAdminUserDTO sysAdminUserDTO);


    /**
     * 加载页面信息
     * @param sysAdminUserDTO
     * @return
     */
    AdminUserDTO loadPage(SysAdminUserDTO sysAdminUserDTO);
}
