package com.starry.sky.domain.service.impl;

import com.starry.sky.application.assembler.AdminUserRoleRelationAppAssembler;
import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.domain.repository.SysAdminUserRoleRelationDORepository;
import com.starry.sky.domain.service.AdminUserRoleRelationDomainService;
import com.starry.sky.infrastructure.dto.SysAdminUserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: 用户角色关联类
 * @date 2021-11-10
 */
@Service
public class AdminUserRoleRelationDomainServiceImpl implements AdminUserRoleRelationDomainService {


    @Autowired
    SysAdminUserRoleRelationDORepository sysAdminUserRoleRelationDORepository;

    @Autowired
    AdminUserRoleRelationAppAssembler adminUserRoleRelationAssembler;

    /**
     * 根据用户ID获取角色ID集合
     *
     * @param sysAdminUserRoleDTO
     * @return
     */
    @Override
    public List<SysAdminUserRoleRelationDO> loadRoleByUserId(SysAdminUserRoleDTO sysAdminUserRoleDTO) {
        List<SysAdminUserRoleRelationDO> listRoleDo =
                sysAdminUserRoleRelationDORepository.findByUserId(sysAdminUserRoleDTO);
        if (listRoleDo == null){
            listRoleDo = Collections.emptyList();
        }
        return listRoleDo;
    }
}
