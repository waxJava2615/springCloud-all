package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.repository.SysAdminPermissionDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminPermissionDORepositoryImpl implements SysAdminPermissionDORepository {

    @Autowired
    SysAdminPermissionRepository sysAdminPermissionRepository;



}
