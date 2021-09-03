package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRepository;
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

    @Override
    public SysAdminUserDO findByUserName(String userName){

        // 添加缓存
        SysAdminUser sysAdminUser = sysAdminUserRepository.findByUserName(userName);

        // 转换DO

        return new SysAdminUserDO();

    }

    @Override
    public SysAdminUserDO findByUserId(Long userId) {

        return null;
    }
}
