package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;

/**
  * @description: TODO
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminUserDORepository {

  /**
   * 根据用户名查找用户
   * @param sysAdminUserDTO
   * @return
   */
  SysAdminUserDO findByUserName(SysAdminUserDTO sysAdminUserDTO);

  /**
   * 根据用户ID查找用户
   * @param sysAdminUserDTO
   * @return
   */
  SysAdminUserDO findByUserId(SysAdminUserDTO sysAdminUserDTO);

}