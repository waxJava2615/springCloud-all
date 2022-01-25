package com.starry.sky.domain.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.domain.service.AdminMenuDomainService;
import com.starry.sky.domain.service.AdminOperationDomainService;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.Pager;
import com.starry.sky.infrastructure.utils.SnowflakeGenerate;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.utils.enums.AdminResultCode;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 菜单
 * @date 2021-11-15
 */
@Slf4j
@Service
public class AdminMenuDomainServiceImpl implements AdminMenuDomainService {


    @Autowired
    private SysAdminMenuDORepository sysAdminMenuDORepository;


    @Autowired
    AdminOperationDomainService adminOperationDomainService;

    @Autowired
    AdminRoleDomainService adminRoleDomainService;

    @Autowired
    SnowflakeGenerate snowflakeGenerate;

    /**
     * 获取菜单 根据hide字段
     * @param sysAdminMenuDTO #hide
     * @return
     */
    @Override
    public List<SysAdminMenuDO> getMenu(SysAdminMenuDTO sysAdminMenuDTO){
        List<SysAdminMenuDO> menuList = sysAdminMenuDORepository.findListByHide(sysAdminMenuDTO);
        return menuList==null? Collections.emptyList() : menuList;
    }

    /**
     * 后台根据（id 或者 父ID 或者 隐藏显示） 分页查询
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public Pager<SysAdminMenuDO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO) {
        Pager<SysAdminMenuDO> pager = sysAdminMenuDORepository.loadMenuList(sysAdminMenuDTO);
        return pager;
    }


    /**
     * 根据ID获取菜单详情
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public SysAdminMenuDO loadMenuById(SysAdminMenuDTO sysAdminMenuDTO) {
        return sysAdminMenuDORepository.findByMenuId(sysAdminMenuDTO);
    }


    /**
     * 根据ID 删除菜单
     *
     * @param sysAdminMenuDTO
     */
    @Override
    public int removeMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenuDO sysAdminMenuDO = sysAdminMenuDORepository.findByMenuId(sysAdminMenuDTO);
        if (sysAdminMenuDO == null){
            throw new CustomizeRuntimeException(ResultCode.INVALID_DATA_ERROR);
        }
        return sysAdminMenuDORepository.removeMenu(sysAdminMenuDTO);
    }


    /**
     * 根据ID修改菜单
     *
     * @param sysAdminMenuDTO
     */
    @Override
    public int editMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenuDO sysAdminMenuDO = sysAdminMenuDORepository.findByMenuId(sysAdminMenuDTO);
        if (sysAdminMenuDO == null){
            throw new CustomizeRuntimeException(ResultCode.INVALID_DATA_ERROR);
        }
        if (sysAdminMenuDTO.getId().equals(sysAdminMenuDTO.getParentId())){
            throw new CustomizeRuntimeException(AdminResultCode.PARENT_ID_INVALID);
        }
        sysAdminMenuDTO.setOnlyKey(sysAdminMenuDO.getOnlyKey());
        SysAdminMenuDO tempMenu = sysAdminMenuDORepository.findByOnlyKey(sysAdminMenuDTO);
        if (tempMenu != null && !sysAdminMenuDO.getId().equals(tempMenu.getId())){
            throw new CustomizeRuntimeException(AdminResultCode.INVALID_MENU_ONLY_KEY);
        }
        Long userId = null;
        try {
            userId = ThreadLocalHolder.getResUtils().get().getPayloadUserId();
        } catch (Exception e) {
           log.error("根据请求头,获取用户ID失败");
        }
        sysAdminMenuDO.editFiled(sysAdminMenuDTO.getName(),sysAdminMenuDTO.getUrl(),sysAdminMenuDTO.getOnlyKey(),
                sysAdminMenuDTO.getParentId(),sysAdminMenuDTO.getOption(),sysAdminMenuDTO.getIcon(),
                sysAdminMenuDTO.getHide(),sysAdminMenuDTO.getOrder(),userId);
        return sysAdminMenuDORepository.update(sysAdminMenuDO);
    }


    /**
     * 添加菜单
     *
     * @param sysAdminMenuDTO
     */
    @Override
    public int pushMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenuDO sysAdminMenuDO = sysAdminMenuDORepository.findByOnlyKey(sysAdminMenuDTO);
        if (sysAdminMenuDO != null){
            throw new CustomizeRuntimeException(AdminResultCode.INVALID_MENU_ONLY_KEY);
        }
        sysAdminMenuDO = BeanUtil.copyProperties(sysAdminMenuDTO, SysAdminMenuDO.class);
        sysAdminMenuDO.setId(snowflakeGenerate.nextId());
        Long userId = null;
        try {
            userId = ThreadLocalHolder.getResUtils().get().getPayloadUserId();
        } catch (Exception e) {
            log.error("根据请求头,获取用户ID失败");
        }
        sysAdminMenuDO.pushFiled(userId);
        return sysAdminMenuDORepository.cusSave(sysAdminMenuDO);
    }

    /**
     * 根据ID获取菜单 并获取父节点对象
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public SysAdminMenuDO loadMenuSelect(SysAdminMenuDTO sysAdminMenuDTO) {
        SysAdminMenuDO sysAdminMenuDO = sysAdminMenuDORepository.findByMenuId(sysAdminMenuDTO);
        sysAdminMenuDTO.defaultPage(CommonConstants.IGNORE_FILED);
        List<SysAdminMenuDO> listAllMenu = sysAdminMenuDORepository.findListByHide(sysAdminMenuDTO);
        Map<Long,SysAdminMenuDO> toIdMap = new HashMap<>();
        listAllMenu.forEach(m->{
            toIdMap.put(m.getId(),m);
        });
        SysAdminMenuDO selectParentMenu = treeParentMenu(toIdMap, sysAdminMenuDTO.getId(),true);
        sysAdminMenuDO.setParentMenu(selectParentMenu);
        return sysAdminMenuDO;
    }


    /**
     * 根据当前ID获取菜单的父节点
     * @param toIdMap
     * @param currentMenuId
     * @return
     */
    private SysAdminMenuDO treeParentMenu(Map<Long,SysAdminMenuDO> toIdMap, Long currentMenuId,
                                          boolean removeCurrentMenuId){
        if (toIdMap == null || toIdMap.isEmpty()){
            return null;
        }
        SysAdminMenuDO sysAdminMenuDO = toIdMap.get(currentMenuId);
        if (sysAdminMenuDO == null){
            return null;
        }
        SysAdminMenuDO pMenuDO = toIdMap.get(sysAdminMenuDO.getParentId());
        if (pMenuDO != null && !removeCurrentMenuId){
            pMenuDO.setChildrenMenu(sysAdminMenuDO);
        }
        SysAdminMenuDO parentMenu = treeParentMenu(toIdMap, sysAdminMenuDO.getParentId(),false);
        // 通过当前 ID获取上一级菜单
        if (parentMenu == null){
            // 表示当前未ROOT
            return pMenuDO;
        }else {
            // 表示当前并非root
            return toIdMap.get(parentMenu.getId());
        }
    }
}
