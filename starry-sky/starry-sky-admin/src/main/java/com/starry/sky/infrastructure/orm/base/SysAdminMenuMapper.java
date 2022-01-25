package com.starry.sky.infrastructure.orm.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import org.apache.ibatis.annotations.Param;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
public interface SysAdminMenuMapper extends BaseMapper<SysAdminMenu> {


    /**
     * 分页获取菜单
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminMenu> findListByHide(Page page,@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据（id 或者 父ID 或者 隐藏显示） 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysAdminMenu> findMenuList(Page<SysAdminMenu> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 获取唯一键的菜单
     * @return
     */
    SysAdminMenu findByOnlyKey(@Param(Constants.WRAPPER) Wrapper wrapper);

}