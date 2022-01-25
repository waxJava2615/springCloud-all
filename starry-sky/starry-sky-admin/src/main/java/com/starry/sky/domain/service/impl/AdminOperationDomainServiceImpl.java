package com.starry.sky.domain.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.domain.entity.SysAdminPermissionDO;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminOperationDORepository;
import com.starry.sky.domain.service.AdminOperationDomainService;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.utils.ResUtils;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author wax
 * @description: 菜单
 * @date 2021-11-15
 */
@Service
public class AdminOperationDomainServiceImpl implements AdminOperationDomainService {



    @Autowired
    SysAdminOperationDORepository sysAdminOperationDORepository;


    @Autowired
    AdminRoleDomainService adminRoleDomainService;



    /**
     * 获取操作
     * @param sysAdminOperationDTO #hide
     * @return
     */
    @Override
    public List<SysAdminOperationDO> getOperation(SysAdminOperationDTO sysAdminOperationDTO){
        List<SysAdminOperationDO> list = sysAdminOperationDORepository.findByHide(sysAdminOperationDTO);
        if (CollectionUtils.isEmpty(list)){
            list = Collections.emptyList();
        }
        return list;
    }


    /**
     * 根据模块 获取操作
     * @return
     */
    @Override
    public Map<String,SysAdminOperationDO>  loadModuleOperation() {
        ThreadLocal<ResUtils> resUtilsThreadLocal = ThreadLocalHolder.getResUtils();
        ResUtils resUtils = resUtilsThreadLocal.get();
        // 模块地址
        String modulePath = CommonConstants.FIX_OBLIQUE + resUtils.getModulePath();
        // 获取当前模块操作的权限映射
        // 获取操作的集合
        SysAdminOperationDTO sysAdminOperationDTO = new SysAdminOperationDTO();
        sysAdminOperationDTO.defaultPage();
        List<SysAdminOperationDO> listOperationDO = this.getOperation(sysAdminOperationDTO);
        // 获取模块操作
        ListMultimap<Long,SysAdminOperationDO> listMultimap = ArrayListMultimap.create();
        AtomicReference<SysAdminOperationDO> currentParent = new AtomicReference<>();
        listOperationDO.forEach(o->{
            if (o.getParentId() != null && o.getParentId() > 0){
                listMultimap.put(o.getParentId(),o);
            }
            if (o.getInterceptUrlPrefix().equals(modulePath)){
                currentParent.set(o);
            }
        });
        // 获取模块下的操作菜单
        SysAdminOperationDO currentParentOperationDO = currentParent.get();
        List<SysAdminOperationDO> currentOperationChildList = Collections.emptyList();
        if (currentParentOperationDO != null){
            currentOperationChildList = listMultimap.get(currentParentOperationDO.getId());
        }
        // 链接对应权限
        ListMultimap<String,String> listCurrentUrlToRole = ArrayListMultimap.create();
        Map<String,SysAdminOperationDO> listCurrentUrlToOperation = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(currentOperationChildList)){
            SysAdminRoleDTO currentSysAdminRoleDTO = new SysAdminRoleDTO();
            currentSysAdminRoleDTO.defaultPage();
            currentSysAdminRoleDTO.setListOperationId(currentOperationChildList.stream().map(SysAdminOperationDO::getId).collect(Collectors.toList()));
            List<SysAdminRoleDO> listRoleDO =  adminRoleDomainService.findRoleByOperationId(currentSysAdminRoleDTO);
            listRoleDO.forEach(d ->{
                String roleName = d.getName();
                List<SysAdminPermissionDO> listSysAdminPermission = d.getListSysAdminPermission();
                listSysAdminPermission.forEach(p ->{
                    List<SysAdminOperationDO> listSysAdminOperation = p.getListSysAdminOperation();
                    listSysAdminOperation.forEach(o->{
                        if (!modulePath.equals(o.getInterceptUrlPrefix())){
                            listCurrentUrlToRole.put(modulePath + o.getInterceptUrlPrefix(),roleName);
                            listCurrentUrlToOperation.put(modulePath + o.getInterceptUrlPrefix(),o);
                        }
                    });
                });
            });
        }

        Map<String, Collection<String>> moduleOperationMap = listCurrentUrlToRole.asMap();
        // 通过获取JWT中的权限  查询操作权限
        List<String> authoritiesList = resUtils.getPayloadAuthorities();
        Map<String,SysAdminOperationDO> roleMap = new HashMap<>();
        for (Map.Entry<String, Collection<String>> entry : moduleOperationMap.entrySet()){
            String modulePush = modulePath + CommonConstants.ROLE_OPERATION_SUFFIX_PUSH;
            String moduleEdit = modulePath + CommonConstants.ROLE_OPERATION_SUFFIX_EDIT;
            String moduleRemove = modulePath + CommonConstants.ROLE_OPERATION_SUFFIX_REMOVE;
            String moduleExport = modulePath + CommonConstants.ROLE_OPERATION_SUFFIX_EXPORT;
            Collection<String> roleColl = entry.getValue();
            authoritiesList.forEach(curr->{
                if (roleColl.contains(curr)){
                    String roleName = null;
                    if (modulePush.equals(entry.getKey())){
                        roleName = CommonConstants.ROLE_OPERATION_PUSH;
                    }
                    if (moduleEdit.equals(entry.getKey())){
                        roleName = CommonConstants.ROLE_OPERATION_EDIT;
                    }
                    if (moduleRemove.equals(entry.getKey())){
                        roleName = CommonConstants.ROLE_OPERATION_REMOVE;
                    }
                    if (moduleExport.equals(entry.getKey())){
                        roleName = CommonConstants.ROLE_OPERATION_EXPORT;
                    }
                    if (StringUtils.isNotEmpty(roleName) && !roleMap.containsKey(roleName)){
                        SysAdminOperationDO sysAdminOperationDO = listCurrentUrlToOperation.get(entry.getKey());
                        roleMap.put(roleName,sysAdminOperationDO);
                    }
                }
            });
        }
        return roleMap;
    }
}
