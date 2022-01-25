package com.starry.sky.application.assembler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.application.dto.AdminRoleDTO;
import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.entity.SysAdminPermissionDO;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-16
 */
@Service
public class AdminRoleAppAssembler {

    public final static int MENU_TYPE = 0;
    public final static int OPERATION_TYPE = 1;


    public List<AdminRoleDTO> convertDTO(SysAdminRoleDO sysAdminRoleDO,Map<Long, AdminMenuDTO> map,
                                   int type){
        if (sysAdminRoleDO == null){
            return null;
        }
        List<AdminRoleDTO> listResult = new ArrayList<>();
        List<SysAdminPermissionDO> listSysAdminPermission = sysAdminRoleDO.getListSysAdminPermission();
        if (CollectionUtils.isNotEmpty(listSysAdminPermission)){
            listSysAdminPermission.forEach(p ->{
                List<String> listUrl = Collections.emptyList();
                if (type == OPERATION_TYPE){
                    List<SysAdminOperationDO> listOperationDO = p.getListSysAdminOperation();
                    if (CollectionUtils.isNotEmpty(listOperationDO)){
                        // 获取到所有菜单集合
                        listUrl =
                                listOperationDO.stream().map(op->fullOperationPath(op,map)).collect(Collectors.toList());
                    }
                }else {
                    List<SysAdminMenuDO> listSysAdminMenu = p.getListSysAdminMenu();
                    if (CollectionUtils.isNotEmpty(listSysAdminMenu)){
                        // 获取到所有菜单集合
                        listUrl =
                                listSysAdminMenu.stream().map(mu->fullMenuPath(map.get(mu.getId()),map)).collect(Collectors.toList());
                    }
                }
                // 组装菜单映射的权限
                listUrl.forEach(lu->{
                    AdminRoleDTO adminRoleDTO = new AdminRoleDTO();
                    List<String> roleList = Lists.newArrayList();
                    roleList.add(sysAdminRoleDO.getName());
                    adminRoleDTO.setRoleList(roleList);
                    adminRoleDTO.setUrl(lu);
                    listResult.add(adminRoleDTO);
                });
            });
        }
        return listResult;
    }

    public List<AdminRoleDTO> convertDTO(List<SysAdminRoleDO> list,Map<Long, AdminMenuDTO> map, int type){
        if (CollectionUtils.isEmpty(list)){
            return Collections.emptyList();
        }
        Map<String, AdminRoleDTO> mapResult = Maps.newHashMap();
        list.forEach(r -> {
            List<AdminRoleDTO> listRoleDTO = convertDTO(r,map,type);
            if (listRoleDTO != null){
                listRoleDTO.forEach(rd->{
                    AdminRoleDTO tempAdminRoleDTO = mapResult.get(rd.getUrl());
                    if (tempAdminRoleDTO != null){
                        List<String> roleList = tempAdminRoleDTO.getRoleList();
                        if (roleList == null){
                            roleList = Collections.emptyList();
                        }
                        roleList.addAll(rd.getRoleList());
                        tempAdminRoleDTO.setRoleList(roleList);
                    }else {
                        tempAdminRoleDTO = rd;
                    }
                    mapResult.put(rd.getUrl(),tempAdminRoleDTO);
                });
            }
        });
        return new ArrayList<>(mapResult.values());
    }


    private String fullMenuPath(AdminMenuDTO adminMenuDTO, Map<Long, AdminMenuDTO> map){
        String path = "";
        if (adminMenuDTO == null || map ==null){
            return path;
        }
        path = fullMenuPath(map.get(adminMenuDTO.getParentId()),map);
        if (StringUtils.isEmpty(path)){
            path = "";
        }
        return path + adminMenuDTO.getUrl();
    }

    private String fullOperationPath(SysAdminOperationDO sysAdminOperationDO, Map<Long, AdminMenuDTO> map){
        String path = "";
        if (sysAdminOperationDO == null || map ==null){
            return path;
        }
        path = fullMenuPath(map.get(sysAdminOperationDO.getParentId()),map);
        if (StringUtils.isEmpty(path)){
            path = "";
        }
        return path + sysAdminOperationDO.getInterceptUrlPrefix();
    }

}
