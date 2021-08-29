package com.starry.sky.domain.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;

/**
  * @description: TODO
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminUserRepository<t extends BaseEntity> extends BaseRepository<SysAdminUser> {

  /**
   * @param userName
   * @return {@link SysAdminUser}
   * @throws
   * @author wax
   * @description: 根据用户名查找用户
   * @date 2021-08-23 17:50 
   */
  SysAdminUser findByUserName(String userName);


  SysAdminUser findByUserId(Long userId);
}