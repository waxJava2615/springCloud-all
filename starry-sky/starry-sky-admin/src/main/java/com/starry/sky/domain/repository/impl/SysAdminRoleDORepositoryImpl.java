package com.starry.sky.domain.repository.impl;

import com.starry.sky.common.constant.StarrySkyAdminLockConstants;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.param.SysAdminRoleParam;
import com.starry.sky.infrastructure.utils.assembler.SysAdminRoleAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminRoleCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
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
public class SysAdminRoleDORepositoryImpl implements  SysAdminRoleDORepository{


    @Autowired
    SysAdminRoleRepository sysAdminRoleRepository;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;

    @Autowired
    SysAdminRoleAssembler sysAdminRoleAssembler;

    @Autowired
    SysAdminRoleCache sysAdminRoleCache;


    @Override
    public List<SysAdminRoleDO> findList(SysAdminRoleParam sysAdminRoleParam) {
        List<SysAdminRole> list = sysAdminRoleCache.findList(sysAdminRoleParam);
        if (list == null){
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findList",()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findList(sysAdminRoleParam.getPageNo(),sysAdminRoleParam.getPageSize());
                if (adminRoleList == null){
                    adminRoleList = new ArrayList<>();
                }
                sysAdminRoleCache.findList(sysAdminRoleParam,adminRoleList);
                return adminRoleList;
            });
        }
        return sysAdminRoleAssembler.poToDoList(list);
    }
    
    @Override
    public List<SysAdminRoleDO> findByIds(SysAdminRoleParam sysAdminRoleParam) {
        List<SysAdminRole> list = sysAdminRoleCache.findByIds(sysAdminRoleParam);
        if (list == null){
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_ROLE_LOCK_NAME + ":findByIds",()->{
                List<SysAdminRole> adminRoleList = sysAdminRoleRepository.findByIds(sysAdminRoleParam.getListRoleId());
                if (adminRoleList == null){
                    adminRoleList = new ArrayList<>();
                }
                sysAdminRoleCache.findByIds(sysAdminRoleParam,adminRoleList);
                return adminRoleList;
            });
        }
        return sysAdminRoleAssembler.poToDoList(list);
    }
}
