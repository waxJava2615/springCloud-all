package com.starry.sky.domain.service.impl;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.domain.service.AdminRoleDomainService;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: 角色
 * @date 2021-11-10
 */
@Service
public class AdminRoleDomainServiceImpl implements AdminRoleDomainService {

    @Autowired
    SysAdminRoleDORepository sysAdminRoleDORepository;

    @Autowired
    SysAdminMenuDORepository sysAdminMenuDORepository;

    /**
     * 根据角色ID获取对应的资源菜单
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {

        List<SysAdminRoleDO> listRoleDo = sysAdminRoleDORepository.findRolePermissionMenu(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(listRoleDo)){
            listRoleDo = Collections.emptyList();
        }

        return listRoleDo;
    }


    /**
     * 根据角色ID获取对应的资源操作
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionOperation(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRoleDO> listRoleDo = sysAdminRoleDORepository.findRolePermissionOperation(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(listRoleDo)){
            listRoleDo = Collections.emptyList();
        }
        return listRoleDo;
    }


    /**
     * 根据操作ID获取权限
     *
     * @param currentSysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRoleByOperationId(SysAdminRoleDTO currentSysAdminRoleDTO) {
        List<SysAdminRoleDO> listRoleDo = sysAdminRoleDORepository.findRoleByOperationId(currentSysAdminRoleDTO);
        if (CollectionUtils.isEmpty(listRoleDo)){
            listRoleDo = Collections.emptyList();
        }
        return listRoleDo;
    }

    /**
     * 根据角色ID列表 获取角色数组
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRoleDO> list = sysAdminRoleDORepository.findByIds(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)){
            list = Collections.emptyList();
        }
        return list;
    }


    /**
     * 根据权限名称查询权限
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findByNames(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRoleDO> list = sysAdminRoleDORepository.findByNames(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)){
            list = Collections.emptyList();
        }
        return list;
    }
}
