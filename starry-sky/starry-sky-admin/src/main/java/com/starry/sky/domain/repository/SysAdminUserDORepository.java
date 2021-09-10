package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.infrastructure.param.SysAdminUserParam;

/**
  * @description: TODO
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminUserDORepository {

  /**
   * 根据用户名查找用户
   * @param sysAdminUserParam
   * @return
   */
  SysAdminUserDO findByUserName(SysAdminUserParam sysAdminUserParam);

  /**
   * 根据用户ID查找用户
   * @param sysAdminUserParam
   * @return
   */
  SysAdminUserDO findByUserId(SysAdminUserParam sysAdminUserParam);

}