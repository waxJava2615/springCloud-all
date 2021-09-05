package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRepository;
import com.starry.sky.infrastructure.utils.cache.provider.SysAdminUserCache;
import com.starry.sky.infrastructure.utils.assembler.SysAdminUseAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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

    @Override
    public SysAdminUserDO findByUserName(SysAdminUserDO sysAdminUserDO){
    
        SysAdminUser sysAdminUser = sysAdminUserCache.getMap(sysAdminUserDO.toMap());
        if (sysAdminUser == null) {
            // 添加缓存
            sysAdminUser = sysAdminUserRepository.findByUserName(sysAdminUserDO.getUserName());
            if (sysAdminUser == null){
                sysAdminUser = SysAdminUser.generateDefault();
            }
            sysAdminUserCache.setMap(new HashMap<>(),sysAdminUser);
        }else {
            if (sysAdminUser.getId() == Long.MAX_VALUE){
                sysAdminUser = null;
            }
        }
        if (sysAdminUser == null){
            return null;
        }
        // 转换DO
        return sysAdminUseAssembler.toDO(sysAdminUser);

    }

    @Override
    public SysAdminUserDO findByUserId(SysAdminUserDO sysAdminUserDO) {
        sysAdminUserRepository.findByUserId(sysAdminUserDO.getId());

        return null;
    }
}
