package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public List<SysAdminRoleDO> findAll() {
       return sysAdminRoleRepository.findAll();
    }
    
    @Override
    public List<SysAdminRoleDO> findByIds(List<Long> listRoleId) {
        return sysAdminRoleRepository.findByIds(listRoleId);
    }
}
