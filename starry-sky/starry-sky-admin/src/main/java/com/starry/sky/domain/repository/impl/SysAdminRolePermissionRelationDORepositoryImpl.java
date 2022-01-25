package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminRolePermissionRelationDO;
import com.starry.sky.domain.repository.SysAdminRolePermissionRelationDORepository;
import com.starry.sky.infrastructure.constant.AdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminRolePermissionRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminRolePermissionRelationRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminRolePermissionRelationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminRolePermissionRelationCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 角色权限仓储实现
 */
@Service
public class SysAdminRolePermissionRelationDORepositoryImpl implements SysAdminRolePermissionRelationDORepository {


    @Autowired
    SysAdminRolePermissionRelationRepository sysAdminRolePermissionRelationRepository;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;

    @Autowired
    SysAdminRolePermissionRelationCache sysAdminRolePermissionRelationCache;

    @Autowired
    SysAdminRolePermissionRelationAssembler sysAdminRolePermissionRelationAssembler;

    @Override
    public List<SysAdminRolePermissionRelationDO> findByRoleId(SysAdminRolePermissionRelationDTO sysAdminRolePermissionRelationDTO) {
        List<SysAdminRolePermissionRelation> list =
                sysAdminRolePermissionRelationCache.findByRoleId(sysAdminRolePermissionRelationDTO);
        if (CollectionUtils.isEmpty(list)) {
            list = redissonLockTemplate.lock(AdminLockConstants.SYS_ADMIN_USER_ROLE_RELATION_LOCK_NAME +
                    ":findByRoleId", ()->{
                List<SysAdminRolePermissionRelation> listSysAdminRolePermissionRelation =
                        sysAdminRolePermissionRelationCache.findByRoleId(sysAdminRolePermissionRelationDTO);
                if (CollectionUtils.isEmpty(listSysAdminRolePermissionRelation)) {
                    listSysAdminRolePermissionRelation =
                            sysAdminRolePermissionRelationRepository.findByRoleId(sysAdminRolePermissionRelationDTO.getListRoleId());
                    if (CollectionUtils.isEmpty(listSysAdminRolePermissionRelation)){
                        listSysAdminRolePermissionRelation = new ArrayList<>();
                        listSysAdminRolePermissionRelation.add(new SysAdminRolePermissionRelation());
                    }
                    sysAdminRolePermissionRelationCache.findByRoleId(sysAdminRolePermissionRelationDTO,
                            listSysAdminRolePermissionRelation);
                }
                return listSysAdminRolePermissionRelation;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault ? Collections.emptyList() : sysAdminRolePermissionRelationAssembler.poToDOList(list);
    }
}
