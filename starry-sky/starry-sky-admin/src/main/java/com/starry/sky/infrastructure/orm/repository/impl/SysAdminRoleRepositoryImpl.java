package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.base.SysAdminRoleMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminRoleRepositoryImpl extends BaseRepositoryImpl<SysAdminRoleMapper,
        SysAdminRole> implements SysAdminRoleRepository<SysAdminRole> {
    
    
    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public List<SysAdminRole> findList(int pageNum, int pageSize) {
        Page<SysAdminRole> page = new Page(pageNum, pageSize, Boolean.FALSE);
        LambdaQueryChainWrapper<SysAdminRole> wrapper = lambdaQuery();
        Page<SysAdminRole> pageList = wrapper.page(page);
        return pageList.getRecords();
    }
    
    @Override
    public List<SysAdminRole> findByIds(List<Long> listRoleId) {
        LambdaQueryWrapper<SysAdminRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminRole::getId, listRoleId);
        List<SysAdminRole> roleList = this.getBaseMapper().selectList(wrapper);
        return roleList;
    }
}
