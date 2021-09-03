package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.sky.infrastructure.orm.base.SysAdminUserRoleRelationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRoleRelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 用户角色仓储实现
 */
@Service
public class SysAdminUserRoleRelationRepositoryImpl  extends ServiceImpl<SysAdminUserRoleRelationMapper, SysAdminUserRoleRelation> implements SysAdminUserRoleRelationRepository<SysAdminUserRoleRelation>, IService<SysAdminUserRoleRelation> {
    
    
    @Override
    public List<SysAdminUserRoleRelation> findByUserId(Long userId) {
    
        LambdaQueryWrapper<SysAdminUserRoleRelation> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysAdminUserRoleRelation::getUserId,userId);
        return this.getBaseMapper().selectList(wrapper);
    }
}
