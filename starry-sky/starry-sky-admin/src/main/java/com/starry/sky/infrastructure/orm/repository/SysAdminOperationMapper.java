package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminOperation;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAdminOperation record);

    int insertSelective(SysAdminOperation record);

    SysAdminOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAdminOperation record);

    int updateByPrimaryKey(SysAdminOperation record);
}