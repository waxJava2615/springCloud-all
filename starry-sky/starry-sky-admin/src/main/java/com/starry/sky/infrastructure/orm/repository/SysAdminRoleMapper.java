package com.starry.sky.infrastructure.orm.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wax
 * @description: 权限查询
 * @date 2021-08-20
 */
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {


    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<SysAdminRole> findByUerId(@Param("userId") Long userId);


}