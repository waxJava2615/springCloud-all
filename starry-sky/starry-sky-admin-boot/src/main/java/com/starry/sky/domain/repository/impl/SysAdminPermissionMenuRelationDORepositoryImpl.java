package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminPermissionMenuRelationDO;
import com.starry.sky.domain.repository.SysAdminPermissionMenuRelationDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminPermissionMenuRelationDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionMenuRelationRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminPermissionMenuRelationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminPermissionMenuRelationCache;
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
 * @description 权限菜单仓储实现
 */
@Service
public class SysAdminPermissionMenuRelationDORepositoryImpl implements SysAdminPermissionMenuRelationDORepository {


    @Autowired
    SysAdminPermissionMenuRelationRepository sysAdminPermissionMenuRelationRepository;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;

    @Autowired
    SysAdminPermissionMenuRelationCache sysAdminPermissionMenuRelationCache;

    @Autowired
    SysAdminPermissionMenuRelationAssembler sysAdminPermissionMenuRelationAssembler;


    @Override
    public List<SysAdminPermissionMenuRelationDO> findByPermissionId(SysAdminPermissionMenuRelationDTO sysAdminPermissionMenuRelationDTO) {

        List<SysAdminPermissionMenuRelation> list =
                sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationDTO);
        if (list == null) {
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LOCK_NAME + ":findByPermissionId", ()->{
                List<SysAdminPermissionMenuRelation> listPermissionMenuRelation =
                        sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationDTO);
                if (listPermissionMenuRelation == null) {
                    listPermissionMenuRelation =
                            sysAdminPermissionMenuRelationRepository.findByPermissionId(sysAdminPermissionMenuRelationDTO.getListOtherId());
                    if (listPermissionMenuRelation == null) {
                        listPermissionMenuRelation = new ArrayList<>();
                        listPermissionMenuRelation.add(new SysAdminPermissionMenuRelation());
                    }
                }
                sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationDTO,
                        listPermissionMenuRelation);
                return listPermissionMenuRelation;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(list);
        return verifyDefault ? null : sysAdminPermissionMenuRelationAssembler.poToDOList(list);
    }
}
