package com.starry.sky.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.starry.sky.application.assembler.AdminMenuAppAssembler;
import com.starry.sky.application.assembler.AdminUserAppAssembler;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.AdminUserDTO;
import com.starry.sky.application.service.AdminUserAppService;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.domain.entity.other.UserPageDO;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.domain.service.AdminUserDomainService;
import com.starry.sky.domain.service.AdminUserRoleRelationDomainService;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.dto.SysAdminUserRoleDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 应用层  事物隔离层   以及 服务编排
 * 应用层实现类不实现业务逻辑，它通过排列组合领域层的领域对象来实现用例，它的职责可表示为“编排和转发”，
 * 即将它要实现的功能委托给一个或多个领域对象来实现，它本身只负责安排工作顺序和拼装操作结果。 Aggregation
 * @date 2021-11-10
 */
@Slf4j
@Service
public class AdminUserAppServiceImpl implements AdminUserAppService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AdminUserDomainService userAdminDomainService;

    @Autowired
    AdminUserRoleRelationDomainService adminUserRoleRelationDomainService;

    @Autowired
    AdminRoleDomainService adminRoleDomainService;


    @Autowired
    AdminMenuAppAssembler adminMenuAppAssembler;

    @Autowired
    AdminUserAppAssembler adminUserAppAssembler;



    @Override
    public AdminUserDTO loadByUserName(SysAdminUserDTO sysAdminUserDTO){
        SysAdminUserDO sysAdminUserDO = null;
        try {
            // 获取用户信息
            sysAdminUserDO = userAdminDomainService.loadByUserName(sysAdminUserDTO);
        }catch (UsernameNotFoundException ex){
            return null;
        }
        // 构建用户角色参数
        SysAdminUserRoleDTO sysAdminUserRoleDTO = new SysAdminUserRoleDTO();
        sysAdminUserRoleDTO.setUserId(sysAdminUserDO.getId());

        // 获取用户对应的角色
        List<SysAdminUserRoleRelationDO> listUserRoleRelation =
                adminUserRoleRelationDomainService.loadRoleByUserId(sysAdminUserRoleDTO);
        List<Long> listRoleId =
                listUserRoleRelation.stream().map(SysAdminUserRoleRelationDO::getRoleId).collect(Collectors.toList());

        List<String> authorityList = Lists.newArrayList();
        // 构建角色查询参数
        SysAdminRoleDTO sysAdminRoleDTO = new SysAdminRoleDTO();
        sysAdminRoleDTO.setListRoleId(listRoleId);

        List<SysAdminRoleDO> roleUserList = adminRoleDomainService.findByIds(sysAdminRoleDTO);
        roleUserList.forEach(r->{
            if (!authorityList.contains(r.getName())){
                authorityList.add(r.getName());
            }
        });
        AdminUserDTO adminUserDTO = adminUserAppAssembler.convertDTO(sysAdminUserDO, CommonConstants.YES);
        adminUserDTO.setAuthorities(authorityList);
        return adminUserDTO;
    }


    /**
     * 加载页面信息
     *
     * @param sysAdminUserDTO
     * @return
     */
    @SneakyThrows
    @Override
    public AdminUserDTO loadPage(SysAdminUserDTO sysAdminUserDTO) {
        UserPageDO userPageDO = userAdminDomainService.loadPage(sysAdminUserDTO);
        log.info("userPageDO value {}",objectMapper.writeValueAsString(userPageDO));
        AdminUserDTO adminUserDTO = adminUserAppAssembler.convertDTO(userPageDO.getSysAdminUserDO(), CommonConstants.NO);
        List<AdminMenuDTO> menuDTOList = adminMenuAppAssembler.convertTreeDTO(userPageDO.getListMenu());
        adminUserDTO.setListMenu(menuDTOList);
        return adminUserDTO;
    }
}
