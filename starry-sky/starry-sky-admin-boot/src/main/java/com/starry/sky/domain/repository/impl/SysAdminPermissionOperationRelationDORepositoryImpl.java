package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminPermissionOperationRelationDO;
import com.starry.sky.domain.repository.SysAdminPermissionOperationRelationDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminPermissionOperationRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionOperationRelationRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminPermissionOperationRelationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminPermissionOperationRelationCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    RedissonLockTemplate redissonLockTemplate;

    @Autowired
    SysAdminPermissionOperationRelationCache sysAdminPermissionOperationRelationCache;

    @Autowired
    SysAdminPermissionOperationRelationAssembler sysAdminPermissionOperationRelationAssembler;


    @Override
    public List<SysAdminPermissionOperationRelationDO> findByPermissionId(SysAdminPermissionOperationRelationDTO sysAdminPermissionOperationRelationDTO) {
        List<SysAdminPermissionOperationRelation> list =
                sysAdminPermissionOperationRelationCache.findByPermissionId(sysAdminPermissionOperationRelationDTO);
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_PERMISSION_OPTION_RELATION_LOCK_NAME + ":findByPermissionId", ()->{
                List<SysAdminPermissionOperationRelation> listPermissionOperationRelation =
                        sysAdminPermissionOperationRelationCache.findByPermissionId(sysAdminPermissionOperationRelationDTO);
                if (listPermissionOperationRelation == null) {
                    listPermissionOperationRelation =
                            sysAdminPermissionOperationRelationRepository.findByPermissionId(sysAdminPermissionOperationRelationDTO.getListOtherId());
                    if (listPermissionOperationRelation == null) {
                        listPermissionOperationRelation = new ArrayList<>();
                        listPermissionOperationRelation.add(new SysAdminPermissionOperationRelation());
                    }
                    sysAdminPermissionOperationRelationCache.findByPermissionId(sysAdminPermissionOperationRelationDTO, listPermissionOperationRelation);
                }
                return listPermissionOperationRelation;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault?null : sysAdminPermissionOperationRelationAssembler.poToDOList(list);
    }
}
