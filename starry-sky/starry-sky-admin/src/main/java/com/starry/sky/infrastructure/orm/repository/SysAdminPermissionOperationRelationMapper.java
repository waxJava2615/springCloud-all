package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminPermissionOperationRelationMapper {
    int insert(SysAdminPermissionOperationRelation record);

    int insertSelective(SysAdminPermissionOperationRelation record);
}