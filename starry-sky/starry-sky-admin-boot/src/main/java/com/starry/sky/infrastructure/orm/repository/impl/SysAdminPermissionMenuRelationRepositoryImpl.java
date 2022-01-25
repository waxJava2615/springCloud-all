package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.infrastructure.orm.base.SysAdminPermissionMenuRelationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionMenuRelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限菜单仓储实现
 */
@Service
public class SysAdminPermissionMenuRelationRepositoryImpl extends BaseRepositoryImpl<SysAdminPermissionMenuRelationMapper, SysAdminPermissionMenuRelation> implements SysAdminPermissionMenuRelationRepository<SysAdminPermissionMenuRelation> {
    
    
    @Override
    public List<SysAdminPermissionMenuRelation> findByPermissionId(List<Long> listPermissionId) {
        
        LambdaQueryWrapper<SysAdminPermissionMenuRelation> wrapper =
                Wrappers.lambdaQuery();
        wrapper.in(SysAdminPermissionMenuRelation::getPermissionId, listPermissionId);
        
        return this.getBaseMapper().selectList(wrapper);
    }
}
