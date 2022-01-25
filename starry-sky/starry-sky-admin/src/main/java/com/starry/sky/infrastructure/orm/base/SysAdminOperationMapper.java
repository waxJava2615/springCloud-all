package com.starry.sky.infrastructure.orm.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import org.apache.ibatis.annotations.Param;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
public interface SysAdminOperationMapper extends BaseMapper<SysAdminOperation> {
    Page<SysAdminOperation> findListByHide(Page page,@Param(Constants.WRAPPER) Wrapper wrapper);
}