package com.starry.sky.domain.repository;

/**
  * @description: TODO
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminUserRepository<SysAdminUser> extends CommonRepository<SysAdminUser>{

  /**
   * @param userName
   * @return {@link SysAdminUser}
   * @throws
   * @author wax
   * @description: 根据用户名查找用户
   * @date 2021-08-23 17:50 
   */
  SysAdminUser findByUserName(String userName);


}