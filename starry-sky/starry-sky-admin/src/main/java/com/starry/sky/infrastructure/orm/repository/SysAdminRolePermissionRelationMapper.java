package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminRolePermissionRelationMapper {
    int insert(SysAdminRolePermissionRelation record);

    int insertSelective(SysAdminRolePermissionRelation record);
}