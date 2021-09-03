package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRolePermissionRelationDO;
import com.starry.sky.domain.repository.SysAdminRolePermissionRelationDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminRolePermissionRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 角色权限仓储实现
 */
@Service
public class SysAdminRolePermissionRelationDORepositoryImpl implements SysAdminRolePermissionRelationDORepository{


    @Autowired
    SysAdminRolePermissionRelationRepository sysAdminRolePermissionRelationRepository;

    @Override
    public List<SysAdminRolePermissionRelationDO> findByRoleId(List<Long> listRoleId) {
        return sysAdminRolePermissionRelationRepository.findByRoleId(listRoleId);
    }
}
