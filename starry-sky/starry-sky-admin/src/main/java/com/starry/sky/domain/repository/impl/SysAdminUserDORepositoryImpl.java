package com.starry.sky.domain.repository.impl;

import com.starry.sky.common.constant.StarrySkyAdminLockConstants;
import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRepository;
import com.starry.sky.infrastructure.param.SysAdminUserParam;
import com.starry.sky.infrastructure.utils.assembler.SysAdminUseAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminUserCache;
import com.starry.sky.infrastructure.utils.cache.provider.CacheKeyManager;
import com.starry.sky.infrastructure.utils.lock.LockCallback;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-23
 */

@Service
public class SysAdminUserDORepositoryImpl implements SysAdminUserDORepository {


    @Autowired
    SysAdminUserRepository sysAdminUserRepository;

    @Autowired
    SysAdminUserCache sysAdminUserCache;

    @Autowired
    SysAdminUseAssembler sysAdminUseAssembler;

    @Autowired
    RedissonLockTemplate redissonLockTemplate;


    @Autowired
    CacheKeyManager cacheKeyManager;

    @Override
    public SysAdminUserDO findByUserName(SysAdminUserParam sysAdminUserParam) {

        SysAdminUser sysAdminUser = sysAdminUserCache.findByUserName(sysAdminUserParam);
        if (sysAdminUser == null) {
            sysAdminUser = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_USER_LOCK_NAME + ":findByUserName",
                    () -> {
                        SysAdminUser sysAdminUser1 = sysAdminUserCache.findByUserName(sysAdminUserParam);
                        if (sysAdminUser1 == null) {
                            // 添加缓存
                            sysAdminUser1 = sysAdminUserRepository.findByUserName(sysAdminUserParam.getUserName());
                            if (sysAdminUser1 == null) {
                                sysAdminUser1 = SysAdminUser.generateDefault();
                            }
                            sysAdminUserCache.findByUserName(sysAdminUserParam, sysAdminUser1);
                        }
                        return sysAdminUser1;
                    });
        }
        // 转换DO
        return sysAdminUser.getId() == Long.MAX_VALUE ? null : sysAdminUseAssembler.poToDO(sysAdminUser);

    }

    @Override
    public SysAdminUserDO findByUserId(SysAdminUserParam sysAdminUserParam) {
        SysAdminUser sysAdminUser = sysAdminUserCache.findByUserId(sysAdminUserParam);
        if (sysAdminUser == null) {
            sysAdminUser = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_USER_LOCK_NAME + ":findByUserId",
                    new LockCallback<SysAdminUser>() {
                        @Override
                        public SysAdminUser doBusiness() {
                            SysAdminUser sysAdminUser = sysAdminUserCache.findByUserId(sysAdminUserParam);
                            if (sysAdminUser == null) {
                                sysAdminUser = sysAdminUserRepository.findByUserId(sysAdminUserParam.getId());
                                if (sysAdminUser == null) {
                                    sysAdminUser = SysAdminUser.generateDefault();
                                }
                                sysAdminUserCache.findByUserId(sysAdminUserParam,sysAdminUser);
                            }
                            return sysAdminUser;
                        }
                    });
        }
        return sysAdminUser.getId() == Long.MAX_VALUE ? null : sysAdminUseAssembler.poToDO(sysAdminUser);
    }
}
