package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starry.sky.infrastructure.orm.base.SysAdminMenuMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 菜单仓储
 * @date 2021-08-20
 */
@Service
public class SysAdminMenuRepositoryImpl extends BaseRepositoryImpl<SysAdminMenuMapper,
        SysAdminMenu> implements SysAdminMenuRepository<SysAdminMenu>, IService<SysAdminMenu> {
    
    
    @Override
    public List<SysAdminMenu> findByMenuId(List<Long> listMenuId) {
        LambdaQueryWrapper<SysAdminMenu> wrapper =
                Wrappers.lambdaQuery();
        wrapper.in(SysAdminMenu::getId, listMenuId);
        
        return this.getBaseMapper().selectList(wrapper);
    }
}