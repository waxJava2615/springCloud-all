package com.starry.sky.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.starry.sky.application.assembler.AdminRoleAppAssembler;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.AdminRoleDTO;
import com.starry.sky.application.service.SourceRoleAppService;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.service.AdminMenuDomainService;
import com.starry.sky.domain.service.AdminOperationDomainService;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 是吸纳
 * @date 2021-11-15
 */
@Service
public class SourceRoleAppServiceImpl implements SourceRoleAppService {


    @Autowired
    AdminRoleDomainService adminRoleDomainService;

    @Autowired
    AdminRoleAppAssembler adminRoleAssembler;

    @Autowired
    AdminMenuDomainService adminMenuDomainService;

    @Autowired
    AdminOperationDomainService adminOperationDomainService;

    /**
     * 获取所有的资源 以及 操作的权限映射
     *
     * @return
     */
    @Override
    public List<AdminRoleDTO> getSourceOperationRole(SysAdminRoleDTO sysAdminRoleDTO) {
        // 获取所有菜单
        SysAdminMenuDTO sysAdminMenuDTO = new SysAdminMenuDTO();
        sysAdminMenuDTO.defaultPage();
        List<SysAdminMenuDO> listMenuDo = adminMenuDomainService.getMenu(sysAdminMenuDTO);
        Map<Long, AdminMenuDTO> menuDtoMap = Maps.newHashMap();
        listMenuDo.forEach(md ->{
            AdminMenuDTO adminMenuDTO = BeanUtil.copyProperties(md, AdminMenuDTO.class);
            menuDtoMap.put(md.getId(),adminMenuDTO);
        });
        // 获取资源菜单
        List<SysAdminRoleDO> listRoleMenu = adminRoleDomainService.findRolePermissionMenu(sysAdminRoleDTO);
        // 获取所有资源
        SysAdminOperationDTO sysAdminOperationDTO = new SysAdminOperationDTO();
        sysAdminOperationDTO.defaultPage();
        List<SysAdminOperationDO> listOperationDo = adminOperationDomainService.getOperation(sysAdminOperationDTO);
        Map<Long, AdminMenuDTO> operationDtoMap = Maps.newHashMap();
        listOperationDo.forEach(od ->{
            AdminMenuDTO adminMenuDTO = BeanUtil.copyProperties(od, AdminMenuDTO.class);
            operationDtoMap.put(od.getId(),adminMenuDTO);
        });
        // 获取操作资源
        List<SysAdminRoleDO> listRoleOperation = adminRoleDomainService.findRolePermissionOperation(sysAdminRoleDTO);
        List<AdminRoleDTO> listRoleDTO = Lists.newArrayList();
        List<AdminRoleDTO> listRoleMenuDTO = adminRoleAssembler.convertDTO(listRoleMenu,menuDtoMap,
                AdminRoleAppAssembler.MENU_TYPE);
        List<AdminRoleDTO> listRoleOperationDTO = adminRoleAssembler.convertDTO(listRoleOperation,operationDtoMap,
                AdminRoleAppAssembler.OPERATION_TYPE);
        listRoleDTO.addAll(listRoleMenuDTO);
        listRoleDTO.addAll(listRoleOperationDTO);
        return listRoleDTO;
    }
}
