package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.sky.infrastructure.orm.base.SysAdminOperationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.orm.repository.SysAdminOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储实现类
 */
@Service
public class SysAdminOperationRepositoryImpl extends ServiceImpl<SysAdminOperationMapper, SysAdminOperation> implements SysAdminOperationRepository<SysAdminOperation>, IService<SysAdminOperation> {
    
    
    @Override
    public List<SysAdminOperation> findByOptionId(List<Long> listOperationId) {
        LambdaQueryWrapper<SysAdminOperation> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminOperation::getId,listOperationId);
        return this.getBaseMapper().selectList(wrapper);
    }
}
