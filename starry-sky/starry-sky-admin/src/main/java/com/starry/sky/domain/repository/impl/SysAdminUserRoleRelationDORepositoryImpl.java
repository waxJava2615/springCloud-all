package com.starry.sky.domain.repository.impl;

import com.starry.sky.common.constant.StarrySkyAdminLockConstants;
import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.domain.repository.SysAdminUserRoleRelationDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRoleRelationRepository;
import com.starry.sky.infrastructure.param.SysAdminUserRoleParam;
import com.starry.sky.infrastructure.utils.assembler.SysAdminUserRoleRelationAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminUserRoleRelationCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private RedissonLockTemplate redissonLockTemplate;

    @Autowired
    private SysAdminUserRoleRelationRepository sysAdminUserRoleRelationRepository;

    @Autowired
    private SysAdminUserRoleRelationCache sysAdminUserRoleRelationCache;

    @Autowired
    private SysAdminUserRoleRelationAssembler sysAdminUserRoleRelationAssembler;

    @Override
    public List<SysAdminUserRoleRelationDO> findByUserId(SysAdminUserRoleParam sysAdminUserRoleParam) {
        List<SysAdminUserRoleRelation> list = sysAdminUserRoleRelationCache.findByUserId(sysAdminUserRoleParam);
        if (list == null){
            list = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_USER_ROLE_RELATION_LOCK_NAME +
                            ":findByUserId", ()->{
                List<SysAdminUserRoleRelation> sysAdminUserRoleRelationList =
                        sysAdminUserRoleRelationCache.findByUserId(sysAdminUserRoleParam);
                if (sysAdminUserRoleRelationList == null) {
                    sysAdminUserRoleRelationList =
                            sysAdminUserRoleRelationRepository.findByUserId(sysAdminUserRoleParam.getUserId());
                    if (sysAdminUserRoleRelationList == null) {
                        sysAdminUserRoleRelationList = new ArrayList<>();
                    }
                    sysAdminUserRoleRelationCache.findByUserId(sysAdminUserRoleParam, sysAdminUserRoleRelationList);
                }
                return sysAdminUserRoleRelationList;
            });
        }
        return sysAdminUserRoleRelationAssembler.poToDoList(list);
    }
}
