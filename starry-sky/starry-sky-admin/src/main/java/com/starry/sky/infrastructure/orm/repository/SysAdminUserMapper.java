package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.SysAdminUser;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAdminUser record);

    int insertSelective(SysAdminUser record);

    SysAdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAdminUser record);

    int updateByPrimaryKey(SysAdminUser record);
}