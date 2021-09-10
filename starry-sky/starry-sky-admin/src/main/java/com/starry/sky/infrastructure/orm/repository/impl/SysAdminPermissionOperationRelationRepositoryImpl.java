package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.domain.repository.impl.BaseRepositoryImpl;
import com.starry.sky.infrastructure.orm.base.SysAdminPermissionOperationRelationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionOperationRelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限操作实现
 */
@Service
public class SysAdminPermissionOperationRelationRepositoryImpl extends BaseRepositoryImpl<SysAdminPermissionOperationRelationMapper, SysAdminPermissionOperationRelation> implements SysAdminPermissionOperationRelationRepository<SysAdminPermissionOperationRelation>{
    
    
    @Override
    public List<SysAdminPermissionOperationRelation> findByPermissionId(List<Long> listPermissionId) {
        LambdaQueryWrapper<SysAdminPermissionOperationRelation> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminPermissionOperationRelation::getPermissionId,listPermissionId);
        return this.getBaseMapper().selectList(wrapper);
    }
}
