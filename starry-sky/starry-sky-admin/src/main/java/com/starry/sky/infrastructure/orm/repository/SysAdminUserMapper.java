package com.starry.sky.infrastructure.orm.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import org.apache.ibatis.annotations.Param;

/**
    * @description: 用户MAPPER
    * @author wax
    * @date 2021-08-20
    */
public interface SysAdminUserMapper extends BaseMapper<SysAdminUser> {

    /**
     * 查询用户
     * @param userName
     * @return
     */
    SysAdminUser findByUserName(@Param("userName") String userName);

    SysAdminUser findByUserId(@Param("userId") Long userId);
}