package com.starry.sky.domain.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.entity.SysAdminPermissionDO;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.entity.other.UserPageDO;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.domain.service.AdminMenuDomainService;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.domain.service.AdminUserDomainService;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.enums.AdminResultCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author wax
 * @description: 后台用户领域服务
 * @date 2021-11-10
 */
@Service
public class AdminUserDomainServiceImpl implements AdminUserDomainService {

    @Autowired
    SysAdminUserDORepository sysAdminUserDORepository;


    @Autowired
    AdminMenuDomainService adminMenuDomainService;


    @Autowired
    AdminRoleDomainService adminRoleDomainService;


    @Override
    public SysAdminUserDO loadByUserName(SysAdminUserDTO sysAdminUserDTO) throws UsernameNotFoundException {
        SysAdminUserDO sysAdminUserDO = sysAdminUserDORepository.findByUserName(sysAdminUserDTO);
        if (sysAdminUserDO == null){
//            throw new CustomizeRuntimeException(ResultCode.AUTHENTICATION_NOT_USER);
            throw new UsernameNotFoundException(AdminResultCode.USER_NOT_EXIST.getMessage());
        }
        return sysAdminUserDO;
    }


    /**
     * 根据用户ID 获取用户信息
     *
     * @param sysAdminUserDTO
     * @return
     */
    @Override
    public SysAdminUserDO loadByUserId(SysAdminUserDTO sysAdminUserDTO) {
        SysAdminUserDO sysAdminUserDO = sysAdminUserDORepository.findByUserId(sysAdminUserDTO);
        if (sysAdminUserDO == null){
            throw new CustomizeRuntimeException(AdminResultCode.USER_NOT_EXIST);
        }
        return sysAdminUserDO;
    }

    /**
     * 用户登录后台后加载用户的界面信息
     *
     * @param sysAdminUserDTO
     */
    @Override
    public UserPageDO loadPage(SysAdminUserDTO sysAdminUserDTO) {
        // 获取用户信息
        SysAdminUserDO sysAdminUserDO = this.loadByUserId(sysAdminUserDTO);
        sysAdminUserDO.checkStatus();

        // 获取所有的菜单
        SysAdminMenuDTO sysAdminMenuDTO = new SysAdminMenuDTO();
        sysAdminMenuDTO.defaultPage();
        sysAdminMenuDTO.setHide(CommonConstants.HIDE_NO);
        List<SysAdminMenuDO> menuList = adminMenuDomainService.getMenu(sysAdminMenuDTO);

        // 获取用户对应的权限ID
//        SysAdminUserRoleDTO sysAdminUserRoleDTO = new SysAdminUserRoleDTO();
//        sysAdminUserRoleDTO.setUserId(sysAdminUserDTO.getId());
//        List<SysAdminUserRoleRelationDO> sysAdminUserRoleRelationDOList =
//                sysAdminUserRoleRelationDORepository.findByUserId(sysAdminUserRoleDTO);
//        if (sysAdminUserRoleRelationDOList == null){
//            sysAdminUserRoleRelationDOList = Collections.emptyList();
//        }
//        List<Long> listRoleId =
//                sysAdminUserRoleRelationDOList.stream().map(SysAdminUserRoleRelationDO::getRoleId).collect(Collectors.toList());


        // 根据JWT 中权限字段反查ID
//        SysAdminRoleDTO currentSysAdminRoleDTO = new SysAdminRoleDTO();
//        currentSysAdminRoleDTO.defaultPage();
//        currentSysAdminRoleDTO.setListNames(sysAdminUserDTO.getAuthoritiesList());
//        List<SysAdminRoleDO> currentRoleDOList = sysAdminRoleDORepository.findByNames(currentSysAdminRoleDTO);
//        if (currentRoleDOList == null){
//            currentRoleDOList = Collections.emptyList();
//        }
//        List<Long> listRoleId =
//                currentRoleDOList.stream().map(SysAdminRoleDO::getId).collect(Collectors.toList());

        // 获取用户对应的菜单列表
        SysAdminRoleDTO sysAdminRoleDTO = new SysAdminRoleDTO();
        sysAdminRoleDTO.defaultPage();
//        sysAdminRoleDTO.setListRoleId(listRoleId);
        // 获取所有菜单对应的权限
        List<SysAdminRoleDO> sysAdminRoleDOList = adminRoleDomainService.findRolePermissionMenu(sysAdminRoleDTO);
        if (sysAdminRoleDOList == null){
            sysAdminRoleDOList = Collections.emptyList();
        }
        Multimap<String,String> urlToRoleMap = ArrayListMultimap.create();
        // 获取连接对应的所有权限
        sysAdminRoleDOList.forEach(r ->{
            List<SysAdminPermissionDO> listSysAdminPermission = r.getListSysAdminPermission();
            listSysAdminPermission.forEach(p->{
                List<SysAdminMenuDO> listSysAdminMenu = p.getListSysAdminMenu();
                listSysAdminMenu.forEach(m->{
                    urlToRoleMap.put(m.getUrl(),r.getName());
                });
            });
        });

        // 用户当前的权限
        List<String> authoritiesList = sysAdminUserDTO.getAuthoritiesList();
        Iterator<SysAdminMenuDO> iterator = menuList.iterator();
        while (iterator.hasNext()){
            SysAdminMenuDO sysAdminMenuDO = iterator.next();
            if (urlToRoleMap.containsKey(sysAdminMenuDO.getUrl())){
                // 链接是需要权限才能访问
                Collection<String> roleColl = urlToRoleMap.get(sysAdminMenuDO.getUrl());
                if (CollectionUtils.isNotEmpty(roleColl)){
                    if (CollectionUtils.isEmpty(authoritiesList)){
                        iterator.remove();
                        continue;
                    }
                    boolean isRole = false;
                    for (String roleName : authoritiesList) {
                        if (roleColl.contains(roleName)){
                            isRole = true;
                            break;
                        }
                    }
                    if (!isRole){
                        iterator.remove();
                    }
                }
            }
        }
//        menuList.forEach(m->{
//            if (urlToRoleMap.containsKey(m.getUrl())){
//                m.setAuthorities(urlToRoleMap.get(m.getUrl()));
//            }
//        });

        UserPageDO userPageDO = new UserPageDO();
        userPageDO.setSysAdminUserDO(sysAdminUserDO);
        userPageDO.setListMenu(menuList);
        return userPageDO;
    }
}
