package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.infrastructure.constant.StarrySkyAdminLockConstants;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRepository;
import com.starry.sky.infrastructure.utils.assembler.SysAdminUseAssembler;
import com.starry.sky.infrastructure.utils.cache.SysAdminUserCache;
import com.starry.sky.infrastructure.utils.lock.RedissonLockTemplate;
import com.starry.sky.infrastructure.utils.validations.SysDefaultValueValidation;
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


    @Override
    public SysAdminUserDO findByUserName(SysAdminUserDTO sysAdminUserDTO) {

        SysAdminUser sysAdminUser = sysAdminUserCache.findByUserName(sysAdminUserDTO);
        if (sysAdminUser == null) {
            sysAdminUser = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_USER_LOCK_NAME +
                    ":findByUserName", ()->{
                SysAdminUser sysAdminUserTemp = sysAdminUserCache.findByUserName(sysAdminUserDTO);
                if (sysAdminUserTemp == null) {
                    // 添加缓存
                    sysAdminUserTemp = sysAdminUserRepository.findByUserName(sysAdminUserDTO.getUserName());
                    if (sysAdminUserTemp == null) {
                        sysAdminUserTemp = new SysAdminUser();
                    }
                    sysAdminUserCache.findByUserName(sysAdminUserDTO, sysAdminUserTemp);
                }
                return sysAdminUserTemp;
            });
        }
        // 转换DO
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(sysAdminUser);
        return verifyDefault ? null : sysAdminUseAssembler.poToDO(sysAdminUser);

    }

    @Override
    public SysAdminUserDO findByUserId(SysAdminUserDTO sysAdminUserDTO) {
        SysAdminUser sysAdminUser = sysAdminUserCache.findByUserId(sysAdminUserDTO);
        if (sysAdminUser == null) {
            sysAdminUser = redissonLockTemplate.lock(StarrySkyAdminLockConstants.SYS_ADMIN_USER_LOCK_NAME +
                    ":findByUserId", ()->{
                SysAdminUser sysAdminUserTemp = sysAdminUserCache.findByUserId(sysAdminUserDTO);
                if (sysAdminUserTemp == null) {
                    sysAdminUserTemp = sysAdminUserRepository.findByUserId(sysAdminUserDTO.getId());
                    if (sysAdminUserTemp == null) {
                        sysAdminUserTemp = new SysAdminUser();
                    }
                    sysAdminUserCache.findByUserId(sysAdminUserDTO, sysAdminUserTemp);
                }
                return sysAdminUserTemp;
            });
        }
        boolean verifyDefault = SysDefaultValueValidation.verifyDefault(sysAdminUser);
        return verifyDefault ? null : sysAdminUseAssembler.poToDO(sysAdminUser);
    }
}
