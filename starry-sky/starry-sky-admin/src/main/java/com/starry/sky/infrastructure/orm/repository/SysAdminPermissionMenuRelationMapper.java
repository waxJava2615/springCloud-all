package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminPermissionMenuRelationMapper {
    int insert(SysAdminPermissionMenuRelation record);

    int insertSelective(SysAdminPermissionMenuRelation record);
}