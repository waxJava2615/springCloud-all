package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminPermission;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminPermissionMapper {
    int insert(SysAdminPermission record);

    int insertSelective(SysAdminPermission record);
}