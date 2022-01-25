package com.starry.sky.domain.service;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.entity.other.UserPageDO;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-10
 */
public interface AdminUserDomainService {


    /**
     * 获取用户信息  包括用户权限
     * @param sysAdminUserDTO
     * @return
     */
    SysAdminUserDO loadByUserName(SysAdminUserDTO sysAdminUserDTO) throws UsernameNotFoundException;


    /**
     * 根据用户ID 获取用户信息
     * @param sysAdminUserDTO
     * @return
     */
    SysAdminUserDO loadByUserId(SysAdminUserDTO sysAdminUserDTO);

    /**
     * 用户登录后台后加载用户的界面信息
     * @param sysAdminUserDTO
     */
    UserPageDO loadPage(SysAdminUserDTO sysAdminUserDTO);
}
