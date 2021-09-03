package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminPermissionMenuRelationDO;
import com.starry.sky.domain.repository.SysAdminPermissionMenuRelationDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionMenuRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限菜单仓储实现
 */
@Service
public class SysAdminPermissionMenuRelationDORepositoryImpl implements SysAdminPermissionMenuRelationDORepository {


    @Autowired
    SysAdminPermissionMenuRelationRepository sysAdminPermissionMenuRelationRepository;
    
    @Override
    public List<SysAdminPermissionMenuRelationDO> findByPermissionId(List<Long> listPermissionId) {
        return sysAdminPermissionMenuRelationRepository.findByPermissionId(listPermissionId);
    }
}
