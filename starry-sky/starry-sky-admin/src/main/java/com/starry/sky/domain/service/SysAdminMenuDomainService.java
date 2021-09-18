package com.starry.sky.domain.service;

import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.vo.SysAdminMenuVO;

/**
 * @author wax
 * @description: 菜单类
 * @date 2021-09-16
 */
public interface SysAdminMenuDomainService {

    boolean updateMenu(SysAdminMenuDTO sysAdminMenuDTO);

    SysAdminMenuVO findById(SysAdminMenuDTO sysAdminMenuDTO);

}
