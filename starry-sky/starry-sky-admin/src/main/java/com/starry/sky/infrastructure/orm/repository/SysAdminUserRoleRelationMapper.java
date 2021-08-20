package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminUserRoleRelationMapper {
    int insert(SysAdminUserRoleRelation record);

    int insertSelective(SysAdminUserRoleRelation record);
}