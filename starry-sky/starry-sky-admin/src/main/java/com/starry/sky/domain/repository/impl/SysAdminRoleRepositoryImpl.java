package com.starry.sky.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.domain.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminRoleRepositoryImpl extends BaseRepositoryImpl<SysAdminRoleMapper, SysAdminRole>
        implements SysAdminRoleRepository<SysAdminRole> {



    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public List<SysAdminRole> findAll() {
        LambdaQueryWrapper<SysAdminRole> wrapper = Wrappers.lambdaQuery();
        List<SysAdminRole> roleList = this.getBaseMapper().selectList(wrapper);
        return roleList;
    }
    
    @Override
    public List<SysAdminRole> findByIds(List<Long> listRoleId) {
        LambdaQueryWrapper<SysAdminRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminRole::getId,listRoleId);
        List<SysAdminRole> roleList = this.getBaseMapper().selectList(wrapper);
        return roleList;
    }
}
