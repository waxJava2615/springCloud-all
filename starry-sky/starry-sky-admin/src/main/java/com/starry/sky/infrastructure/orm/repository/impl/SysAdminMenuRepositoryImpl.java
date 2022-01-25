package com.starry.sky.infrastructure.orm.repository.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starry.sky.infrastructure.orm.base.SysAdminMenuMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import com.starry.sky.infrastructure.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        QueryWrapper<SysAdminMenu> wrapper = Wrappers.query();
        wrapper.eq("id",sysAdminMenu.getId());
        return this.getBaseMapper().update(sysAdminMenu,wrapper);
    }


    /**
     * 获取菜单列表
     * @param pageNum
     * @param pageSize
     * @param includeHidden
     * @return
     */
    @Override
    public List<SysAdminMenu> findListByHide(int pageNum,int pageSize,int includeHidden){
        Page<SysAdminMenu> page = new Page<>(pageNum,pageSize,false);
        QueryWrapper<SysAdminMenu> wrapper = Wrappers.query();
//        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query() ;
        if (includeHidden != -1){
            wrapper.eq("hide",includeHidden);
        }
        wrapper.orderByDesc("`order`");
        return this.getBaseMapper().findListByHide(page,wrapper).getRecords();
    }


    /**
     * 根据（id 或者 父ID 或者 隐藏显示） 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param hide
     * @param id
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public Pager<SysAdminMenu> findMenuList(Integer pageNo, Integer pageSize, Integer hide, Long id, Long parentId,
                                            String name) {
        Page<SysAdminMenu> page = new Page<>(pageNo,pageSize,true);
        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query() ;

        if (Convert.convert(Long.class,id,-1L) != -1){
            wrapper.eq("id",id);
        }

        if (Convert.convert(Long.class,parentId,-1L) != -1){
            wrapper.eq("parent",parentId);
        }

        if (!Convert.convert(Integer.class,hide,-1).equals(-1)){
            wrapper.eq("hide",hide);
        }

        if (StringUtils.isNotEmpty(name)){
            wrapper.like("name",name);
        }
        wrapper.orderByDesc("`order`");
        Page<SysAdminMenu> pageTemp = this.getBaseMapper().findMenuList(page,wrapper);
        Pager<SysAdminMenu> pager = new Pager<>(pageNo,pageSize);
        pager.setTotal(pageTemp.getTotal());
        pager.setList(pageTemp.getRecords());
        return pager;
    }

    /**
     * 根据唯一KEY查询
     *
     * @param onlyKey
     * @return
     */
    @Override
    public SysAdminMenu findByOnlyKey(String onlyKey) {
        if (StringUtils.isEmpty(onlyKey)){
            return null;
        }
        QueryWrapper<SysAdminMenu> wrapper = Wrappers.query();
        if (StringUtils.isNotEmpty(onlyKey)){
            wrapper.eq("only_key",onlyKey);
        }
        return this.getBaseMapper().findByOnlyKey(wrapper);
    }

    @Override
    public int cusSave(SysAdminMenu sysAdminMenu) {
        if (sysAdminMenu == null){
            return 0;
        }
        return this.getBaseMapper().insert(sysAdminMenu);
    }
}