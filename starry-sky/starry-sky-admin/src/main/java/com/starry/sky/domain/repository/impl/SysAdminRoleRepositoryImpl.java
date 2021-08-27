package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.repository.SysAdminRoleRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.repository.SysAdminRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 角色仓储
 * @date 2021-08-23
 */

@Service
public class SysAdminRoleRepositoryImpl extends CommonRepositoryImpl<SysAdminRoleMapper, SysAdminRole> implements SysAdminRoleRepository<SysAdminRole> {


    /**
     * @param userId
     * @return {@link SysAdminRole}
     * @throws
     * @author wax
     * @description: 根据用户ID查詢角色
     * @date 2021-08-23 17:50
     */
    @Override
    public List<SysAdminRole> findByUserId(Long userId) {
        List<SysAdminRole> roleList = this.getBaseMapper().findByUerId(userId);
        return roleList;
    }
}
