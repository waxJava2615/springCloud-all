package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.base.SysAdminOperationMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import com.starry.sky.infrastructure.orm.repository.SysAdminOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储实现类
 */
@Service
public class SysAdminOperationRepositoryImpl extends BaseRepositoryImpl<SysAdminOperationMapper,
        SysAdminOperation> implements SysAdminOperationRepository<SysAdminOperation> {
    
    
    @Override
    public List<SysAdminOperation> findByOptionId(List<Long> listOperationId) {
        LambdaQueryWrapper<SysAdminOperation> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminOperation::getId, listOperationId);
        return this.getBaseMapper().selectList(wrapper);
    }


    /**
     * 分页查询操作
     *
     * @param pageNo
     * @param pageSize
     * @param includeHidden
     * @return
     */
    @Override
    public List<SysAdminOperation> findListByHide(int pageNo, int pageSize, int includeHidden) {
        Page<SysAdminOperation> page = new Page<>(pageNo,pageSize,false);
        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query() ;
        if (includeHidden != -1){
            wrapper.eq("hide",includeHidden);
        }
        wrapper.orderByDesc("`order`");
        return this.getBaseMapper().findListByHide(page,wrapper).getRecords();
    }
}
