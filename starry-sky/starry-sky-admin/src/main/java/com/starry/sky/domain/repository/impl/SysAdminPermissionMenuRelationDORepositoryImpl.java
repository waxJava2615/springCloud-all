package com.starry.sky.domain.repository.impl;

import com.starry.sky.common.constant.StarrySkyAdminLockConstants;
import com.starry.sky.domain.entity.SysAdminPermissionMenuRelationDO;
import com.starry.sky.domain.repository.SysAdminPermissionMenuRelationDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminPermissionMenuRelationRepository;
import com.starry.sky.infrastructure.param.SysAdminPermissionMenuRelationParam;
import com.starry.sky.infrastructure.utils.assembler.SysAdminPermissionMenuRelationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminPermissionMenuRelationCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
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
    public List<SysAdminPermissionMenuRelationDO> findByPermissionId(SysAdminPermissionMenuRelationParam sysAdminPermissionMenuRelationParam) {
        
        List<SysAdminPermissionMenuRelation> list =
                sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationParam);
        if (list == null) {
            list =
                    redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_PERMISSION_MENU_RELATION_LOCK_NAME + ":findByPermissionId",
                    () -> {
                        List<SysAdminPermissionMenuRelation> listPermissionMenuRelation =
                                sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationParam);
                        if (listPermissionMenuRelation == null){
                            listPermissionMenuRelation =
                                    sysAdminPermissionMenuRelationRepository.findByPermissionId(sysAdminPermissionMenuRelationParam.getListOtherId());
                            if (listPermissionMenuRelation == null){
                                listPermissionMenuRelation = new ArrayList<>();
                            }
                        }
                        sysAdminPermissionMenuRelationCache.findByPermissionId(sysAdminPermissionMenuRelationParam,listPermissionMenuRelation);
                        return listPermissionMenuRelation;
                    });
        }
        
        return sysAdminPermissionMenuRelationAssembler.poToDOList(list);
    }
}
