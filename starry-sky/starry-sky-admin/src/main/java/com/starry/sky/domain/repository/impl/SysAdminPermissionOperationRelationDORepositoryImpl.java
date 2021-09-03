package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminPermissionOperationRelationDO;
import com.starry.sky.domain.repository.SysAdminPermissionOperationRelationDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionOperationRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限操作实现
 */
@Service
public class SysAdminPermissionOperationRelationDORepositoryImpl implements SysAdminPermissionOperationRelationDORepository {


    @Autowired
    SysAdminPermissionOperationRelationRepository sysAdminPermissionOperationRelationRepository;


    @Override
    public List<SysAdminPermissionOperationRelationDO> findByPermissionId(List<Long> listPermissionId) {
        return sysAdminPermissionOperationRelationRepository.findByPermissionId(listPermissionId);
    }
}
