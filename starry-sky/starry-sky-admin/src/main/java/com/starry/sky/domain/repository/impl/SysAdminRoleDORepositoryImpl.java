package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.constant.AdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminRoleAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminRoleCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminRoleDORepositoryImpl implements SysAdminRoleDORepository {


    @Autowired
    SysAdminRoleRepository sysAdminRoleRepository;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;

    @Autowired
    SysAdminRoleAssembler sysAdminRoleAssembler;

    @Autowired
    SysAdminRoleCache sysAdminRoleCache;


    @Override
    public List<SysAdminRoleDO> findList(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findList(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findList", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findList(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findList(sysAdminRoleDTO.getPageNo(),
                            sysAdminRoleDTO.getPageSize());
                    if (CollectionUtils.isEmpty(adminRoleList)) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                }
                sysAdminRoleCache.findList(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }

    @Override
    public List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findByIds(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findByIds:" + StringUtils.join(sysAdminRoleDTO.getListRoleId(), ","), ()->{

                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findByIds(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findByIds(sysAdminRoleDTO.getListRoleId());
                    if (CollectionUtils.isEmpty(adminRoleList)) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                }
                sysAdminRoleCache.findByIds(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }


    /**
     * 获取角色对应的菜单
     * @param sysAdminRoleDTO #listRoleId pageNum pageSize hide
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findRolePermissionMenu", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findRolePermissionMenu(sysAdminRoleDTO.getListRoleId(),sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                    if (CollectionUtils.isEmpty(adminRoleList )) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                    sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO, adminRoleList);
                }
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault? Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }


    /**
     * 获取权限对应的操作
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionOperation(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findRolePermissionOperation(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findRolePermissionOperation", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findRolePermissionOperation(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findRolePermissionOperation(sysAdminRoleDTO.getListRoleId(),sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                    if (CollectionUtils.isEmpty(adminRoleList )) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                    sysAdminRoleCache.findRolePermissionOperation(sysAdminRoleDTO, adminRoleList);
                }
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault? Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }


    /**
     * 根据操作ID获取角色列表
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRoleByOperationId(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findRoleByOperationId(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findRoleByOperationId", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findRoleByOperationId(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findRoleByOperationId(sysAdminRoleDTO.getListOperationId(),sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                    if (CollectionUtils.isEmpty(adminRoleList )) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                    sysAdminRoleCache.findRoleByOperationId(sysAdminRoleDTO, adminRoleList);
                }
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault? Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }

    /**
     * 根据权限名称查询权限
     *
     * @param sysAdminRoleDTO
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findByNames(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findByNames(sysAdminRoleDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findByNames", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findByNames(sysAdminRoleDTO);
                if (CollectionUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findByNames(sysAdminRoleDTO.getListNames(),
                            sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                    if (CollectionUtils.isEmpty(adminRoleList )) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                    sysAdminRoleCache.findByNames(sysAdminRoleDTO, adminRoleList);
                }
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault? Collections.emptyList() : sysAdminRoleAssembler.poToDOList(list);
    }
}
