package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.infrastructure.orm.base.SysAdminRolePermissionRelationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminRolePermissionRelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 角色权限仓储实现
 */
@Service
public class SysAdminRolePermissionRelationRepositoryImpl extends BaseRepositoryImpl<SysAdminRolePermissionRelationMapper, SysAdminRolePermissionRelation> implements SysAdminRolePermissionRelationRepository<SysAdminRolePermissionRelation> {


    @Override
    public List<SysAdminRolePermissionRelation> findByRoleId(List<Long> listRoleId) {
        LambdaQueryWrapper<SysAdminRolePermissionRelation> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminRolePermissionRelation::getRoleId, listRoleId);
        return this.getBaseMapper().selectList(wrapper);
    }
}
