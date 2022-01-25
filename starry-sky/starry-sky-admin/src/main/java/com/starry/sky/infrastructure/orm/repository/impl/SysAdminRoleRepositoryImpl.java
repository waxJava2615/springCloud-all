package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starry.sky.infrastructure.orm.base.SysAdminRoleMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Slf4j
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

    /**
     * 获取角色对应的菜单
     * @param listRoleId
     * @param pageNum
     * @param pageSize
     * @param includeHide 所有hide字段必须为显示
     * @return
     */
    @Override
    public List<SysAdminRole> findRolePermissionMenu(List<Long> listRoleId,int pageNum, int pageSize,int includeHide) {
        QueryWrapper<Map<String,Object>> wrapperParam = Wrappers.query();
        Page<SysAdminRole> page = new Page<>(pageNum,pageSize,false);
        if (listRoleId != null){
            if (listRoleId.size() == 1 ){
                wrapperParam.eq("r.id",listRoleId.get(0));
            }else if (listRoleId.size() > 1){
                wrapperParam.in("r.id",listRoleId);
            }
        }
        if (includeHide != -1){
            wrapperParam.eq("r.hide",includeHide);
            wrapperParam.eq("p.hide",includeHide);
            wrapperParam.eq("m.hide",includeHide);
        }
        log.info(wrapperParam.getCustomSqlSegment());
        log.info(wrapperParam.getSqlSegment());
        log.info(wrapperParam.isEmptyOfWhere() + "");
        log.info("sql Segment ==============================");
        page = this.getBaseMapper().findRolePermissionMenu(page, wrapperParam);
        List<SysAdminRole> list = page.getRecords();
        return list;
    }


    /**
     * 获取角色对应的 操作
     *
     * @param listRoleId
     * @param pageNo
     * @param pageSize
     * @param includeHide
     * @return
     */
    @Override
    public List<SysAdminRole> findRolePermissionOperation(List<Long> listRoleId, Integer pageNo, Integer pageSize,
                                                          Integer includeHide) {
        Page<SysAdminRole> page = new Page<>(pageNo,pageSize,false);
        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query();
        if (listRoleId != null){
            if (listRoleId.size() == 1 ){
                wrapper.eq("r.id",listRoleId.get(0));
            }else if (listRoleId.size() > 1){
                wrapper.in("r.id",listRoleId);
            }
        }
        if (includeHide != -1){
            wrapper.eq("r.hide",includeHide);
        }
        page = this.getBaseMapper().findRolePermissionOperation(page, wrapper);
        List<SysAdminRole> list = page.getRecords();
        return list;
    }


    /**
     * 根据权限名称查询
     *
     * @param listNames
     * @param pageNo
     * @param pageSize
     * @param hide
     * @return
     */
    @Override
    public List<SysAdminRole> findByNames(List<String> listNames, Integer pageNo, Integer pageSize, Integer hide) {
        Page<SysAdminRole> page = new Page<>(pageNo,pageSize,false);
//        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query();
//        if (listNames != null){
//            if (listNames.size() == 1 ){
//                wrapper.eq("r.name",listNames.get(0));
//            }else if (listNames.size() > 1){
//                wrapper.in("r.name",listNames);
//            }
//        }
        LambdaQueryWrapper<SysAdminRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAdminRole::getName, listNames);

        page = this.getBaseMapper().findByNames(page, wrapper);
        return page.getRecords();
    }


    /**
     * 根据操作ID获取权限
     *
     * @param listOperationId
     * @param pageNo
     * @param pageSize
     * @param hide
     * @return
     */
    @Override
    public List<SysAdminRole> findRoleByOperationId(List<Long> listOperationId, Integer pageNo, Integer pageSize,
                                                    Integer includeHide) {
        Page<SysAdminRole> page = new Page<>(pageNo,pageSize,false);
        QueryWrapper<Map<String,Object>> wrapper = Wrappers.query();
        if (listOperationId != null){
            if (listOperationId.size() == 1 ){
                wrapper.eq("o.id",listOperationId.get(0));
            }else if (listOperationId.size() > 1){
                wrapper.in("o.id",listOperationId);
            }
        }
        if (includeHide != -1){
            wrapper.eq("o.hide",includeHide);
        }
        page = this.getBaseMapper().findRoleByOperationId(page, wrapper);
        List<SysAdminRole> list = page.getRecords();
        return list;
    }
}
