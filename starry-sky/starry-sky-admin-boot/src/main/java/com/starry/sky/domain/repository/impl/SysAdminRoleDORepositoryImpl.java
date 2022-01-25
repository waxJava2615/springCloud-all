package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.utils.ArrayerUtils;
import com.starry.sky.infrastructure.utils.assembler.SysAdminRoleAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminRoleCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (ArrayerUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findList", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findList(sysAdminRoleDTO.getPageNo(),
                        sysAdminRoleDTO.getPageSize());
                if (ArrayerUtils.isEmpty(adminRoleList)) {
                    adminRoleList = new ArrayList<>();
                    adminRoleList.add(new SysAdminRole());
                }
                sysAdminRoleCache.findList(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?null : sysAdminRoleAssembler.poToDOList(list);
    }

    @Override
    public List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findByIds(sysAdminRoleDTO);
        if (ArrayerUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findByIds:" + StringUtils.join(sysAdminRoleDTO.getListRoleId(), ","), ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findByIds(sysAdminRoleDTO.getListRoleId());
                if (ArrayerUtils.isEmpty(adminRoleList)) {
                    adminRoleList = new ArrayList<>();
                    adminRoleList.add(new SysAdminRole());
                }
                sysAdminRoleCache.findByIds(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?null : sysAdminRoleAssembler.poToDOList(list);
    }


    /**
     * 获取角色对应的菜单
     * @param sysAdminRoleDTO #listRoleId pageNum pageSize hide
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO);
        if (ArrayerUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findRolePermissionMenu", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO);
                if (ArrayerUtils.isEmpty(adminRoleList)){
                    adminRoleList = sysAdminRoleRepository.findRolePermissionMenu(sysAdminRoleDTO.getListRoleId(),sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                    if (adminRoleList == null) {
                        adminRoleList = new ArrayList<>();
                        adminRoleList.add(new SysAdminRole());
                    }
                    sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO, adminRoleList);
                }
                return adminRoleList;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?null : sysAdminRoleAssembler.poToDOList(list);
    }
}
