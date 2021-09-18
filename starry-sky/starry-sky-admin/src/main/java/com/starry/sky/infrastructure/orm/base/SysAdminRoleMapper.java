package com.starry.sky.infrastructure.orm.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author wax
 * @description: 连表查询
 * @date 2021-08-30
 */
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    Page<SysAdminRole> findRolePermissionMenu(Page page,@Param(Constants.WRAPPER) Wrapper wrapper);
}