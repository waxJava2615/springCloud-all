package com.starry.sky.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.application.assembler.AdminMenuAppAssembler;
import com.starry.sky.application.assembler.AdminOperationAppAssembler;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.AdminOperationDTO;
import com.starry.sky.application.dto.PagerDTO;
import com.starry.sky.application.service.AdminMenuAppService;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.service.AdminMenuDomainService;
import com.starry.sky.domain.service.AdminOperationDomainService;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.infrastructure.constant.AdminConstant;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.utils.Pager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 菜单实现类
 * @date 2021-12-17
 */
@Slf4j
@Service
public class AdminMenuAppServiceImpl implements AdminMenuAppService {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AdminMenuDomainService adminMenuDomainService;

    @Autowired
    AdminMenuAppAssembler adminMenuAppAssembler;

    @Autowired
    AdminRoleDomainService adminRoleDomainService;

    @Autowired
    AdminOperationDomainService adminOperationDomainService;


    @Autowired
    AdminOperationAppAssembler adminOperationAppAssembler;


    /**
     * 后台获取菜单列表
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public PagerDTO<AdminMenuDTO> loadMenuList(SysAdminMenuDTO sysAdminMenuDTO) {
        Pager<SysAdminMenuDO> pager = adminMenuDomainService.loadMenuList(sysAdminMenuDTO);
        List<AdminMenuDTO> dtoList = adminMenuAppAssembler.convertDTO(pager.getList());

        PagerDTO<AdminMenuDTO> pagerResult = new PagerDTO<>(pager.getPageNo(),pager.getTotal(),pager.getTotal());
        pagerResult.setList(dtoList);
        Map<String, SysAdminOperationDO> operationDOMap =
                adminOperationDomainService.loadModuleOperation();
        Map<String, AdminOperationDTO> operationDTOMap = new HashMap<>();
        for (Map.Entry<String,SysAdminOperationDO> entry:operationDOMap.entrySet()){
            AdminOperationDTO adminOperationDTO = adminOperationAppAssembler.convertDTO(entry.getValue(),false);
            operationDTOMap.put(entry.getKey(),adminOperationDTO);
        }
        pagerResult.setRoleOperation(operationDTOMap);
        return pagerResult;
    }


    /**
     * 根据ID获取菜单信息
     *
     * @param sysAdminMenuDTO
     * @return
     */
    @Override
    public AdminMenuDTO loadMenuSelect(SysAdminMenuDTO sysAdminMenuDTO) {
//        SysAdminMenuDO sysAdminMenuDO = adminMenuDomainService.loadMenuById(sysAdminMenuDTO);
        SysAdminMenuDO sysAdminMenuDO = adminMenuDomainService.loadMenuSelect(sysAdminMenuDTO);
        return adminMenuAppAssembler.convertDTO(sysAdminMenuDO);
    }


    /**
     * 加载菜单树结构
     */
    @SneakyThrows
    @Override
    public List<AdminMenuDTO> loadMenuTree() {
        SysAdminMenuDTO sysAdminMenuDTO = new SysAdminMenuDTO();
        sysAdminMenuDTO.defaultPage();
        sysAdminMenuDTO.setHide(AdminConstant.IGNORE);
        List<SysAdminMenuDO> listMenuDO = adminMenuDomainService.getMenu(sysAdminMenuDTO);
        if (CollectionUtils.isEmpty(listMenuDO)){
            return Collections.emptyList();
        }
        List<AdminMenuDTO> listResult = adminMenuAppAssembler.convertTreeDTO(listMenuDO);
        log.debug("listResult :" + objectMapper.writeValueAsString(listResult));
        return listResult;

    }


    /**
     * 根据ID删除菜单
     *
     * @param sysAdminMenuDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        return adminMenuDomainService.removeMenu(sysAdminMenuDTO);
    }

    /**
     * 根据ID修改菜单
     *
     * @param sysAdminMenuDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int editMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        return adminMenuDomainService.editMenu(sysAdminMenuDTO);
    }


    /**
     * 添加菜单
     *
     * @param sysAdminMenuDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int pushMenu(SysAdminMenuDTO sysAdminMenuDTO) {
        return adminMenuDomainService.pushMenu(sysAdminMenuDTO);
    }
}
