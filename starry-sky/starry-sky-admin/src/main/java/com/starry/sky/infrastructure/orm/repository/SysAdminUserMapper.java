package com.starry.sky.infrastructure.orm.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminUserMapper extends BaseMapper<SysAdminUser> {


    @Select("select * from sys_admin_user where user_name = #{userName}")
    SysAdminUser findByUserName(@Param("userName") String userName);

}