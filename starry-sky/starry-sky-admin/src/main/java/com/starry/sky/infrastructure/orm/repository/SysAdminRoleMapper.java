package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminRole;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAdminRole record);

    int insertSelective(SysAdminRole record);

    SysAdminRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAdminRole record);

    int updateByPrimaryKey(SysAdminRole record);
}