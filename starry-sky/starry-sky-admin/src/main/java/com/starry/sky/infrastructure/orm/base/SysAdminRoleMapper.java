package com.starry.sky.infrastructure.orm.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author wax
 * @description: 权限查询
 * @date 2021-08-30
 */
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    /**
     * 连表查询获取权限映射的菜单
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminRole> findRolePermissionMenu(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 获取角色对应的 操作
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminRole> findRolePermissionOperation(Page page,@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据名称集合查询权限
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminRole> findByNames(Page<SysAdminRole> page,
                                   @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据操作ID获取权限
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminRole> findRoleByOperationId(Page page,@Param(Constants.WRAPPER) Wrapper wrapper);
}