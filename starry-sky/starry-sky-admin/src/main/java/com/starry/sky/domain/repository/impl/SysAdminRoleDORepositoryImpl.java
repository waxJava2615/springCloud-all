package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminRoleAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminRoleCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
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
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findList:" + sysAdminRoleDTO.getPageNo() + ":" + sysAdminRoleDTO.getPageSize(), ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findList(sysAdminRoleDTO.getPageNo(),
                        sysAdminRoleDTO.getPageSize());
                if (adminRoleList == null) {
                    adminRoleList = new ArrayList<>();
                }
                sysAdminRoleCache.findList(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        return sysAdminRoleAssembler.poToDOList(list);
    }

    @Override
    public List<SysAdminRoleDO> findByIds(SysAdminRoleDTO sysAdminRoleDTO) {
        List<SysAdminRole> list = sysAdminRoleCache.findByIds(sysAdminRoleDTO);
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findByIds:" + StringUtils.join(sysAdminRoleDTO.getListRoleId(), ","), ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findByIds(sysAdminRoleDTO.getListRoleId());
                if (adminRoleList == null) {
                    adminRoleList = new ArrayList<>();
                }
                sysAdminRoleCache.findByIds(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        return sysAdminRoleAssembler.poToDOList(list);
    }


    /**
     * 获取角色对应的菜单
     * @param sysAdminRoleDTO #listRoleId pageNum pageSize hide
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findRolePermissionMenu(SysAdminRoleDTO sysAdminRoleDTO) {


        List<SysAdminRole> list = sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO);
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findRolePermissionMenu", ()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findRolePermissionMenu(sysAdminRoleDTO.getListRoleId(),sysAdminRoleDTO.getPageNo(),sysAdminRoleDTO.getPageSize(),sysAdminRoleDTO.getHide());
                if (adminRoleList == null) {
                    adminRoleList = new ArrayList<>();
                }
                sysAdminRoleCache.findRolePermissionMenu(sysAdminRoleDTO, adminRoleList);
                return adminRoleList;
            });
        }
        return sysAdminRoleAssembler.poToDOList(list);
    }
}
