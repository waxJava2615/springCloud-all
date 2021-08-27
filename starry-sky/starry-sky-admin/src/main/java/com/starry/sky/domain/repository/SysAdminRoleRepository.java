package com.starry.sky.domain.repository;

import java.util.List;

/**
  * @description: TODO
  * @author wax
  * @date 2021-08-20
  */
public interface SysAdminRoleRepository<SysAdminRole> extends CommonRepository<SysAdminRole>{

  /**
   * @param userId
   * @return {@link SysAdminRole}
   * @throws
   * @author wax
   * @description: 根据用户ID查詢角色
   * @date 2021-08-23 17:50 
   */
  List<SysAdminRole> findByUserId(Long userId);


}