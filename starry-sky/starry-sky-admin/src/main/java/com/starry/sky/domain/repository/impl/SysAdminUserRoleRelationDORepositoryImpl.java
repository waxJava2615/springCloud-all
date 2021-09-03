package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.domain.repository.SysAdminUserRoleRelationDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRoleRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 用户角色仓储实现
 */
@Service
public class SysAdminUserRoleRelationDORepositoryImpl  implements SysAdminUserRoleRelationDORepository {

    @Autowired
    SysAdminUserRoleRelationRepository sysAdminUserRoleRelationRepository;
    
    
    @Override
    public List<SysAdminUserRoleRelationDO> findByUserId(Long userId) {
        return sysAdminUserRoleRelationRepository.findByUserId(userId);
    }
}
