package com.starry.sky.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.domain.repository.SysAdminPermissionRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminPermission;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminPermissionRepositoryImpl extends BaseRepositoryImpl<SysAdminPermissionMapper,
        SysAdminPermission> implements SysAdminPermissionRepository<SysAdminPermission> {
    

    @Override
    public List<SysAdminPermission> findAll() {
        LambdaQueryWrapper<SysAdminPermission> wrapper = Wrappers.lambdaQuery();
        return this.getBaseMapper().selectList(wrapper);
    }
}
