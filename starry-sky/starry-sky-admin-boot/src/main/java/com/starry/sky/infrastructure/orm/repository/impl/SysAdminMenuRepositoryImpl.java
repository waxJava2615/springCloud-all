package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
    public List<SysAdminMenu> findByMenuIdList(List<Long> listMenuId) {
        LambdaQueryWrapper<SysAdminMenu> wrapper =
                Wrappers.lambdaQuery();
        wrapper.in(SysAdminMenu::getId, listMenuId);
        
        return this.getBaseMapper().selectList(wrapper);
    }


    @Override
    public SysAdminMenu findByMenuId(long menuId){
        return this.getBaseMapper().selectById(menuId);
    }

    /**
     * 返回旧的数据对象 用于清除缓存
     * @param sysAdminMenu
     * @return
     */
    @Override
    public int updateByMenuId(SysAdminMenu sysAdminMenu){
        LambdaQueryChainWrapper<SysAdminMenu> lambdaQuery = lambdaQuery();
        lambdaQuery.eq(SysAdminMenu::getId,sysAdminMenu.getId());
        return this.getBaseMapper().update(sysAdminMenu,lambdaQuery);
    }



}