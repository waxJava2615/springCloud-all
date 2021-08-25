package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.repository.SysAdminUserRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-23
 */

@Service
public class SysAdminUserRepositoryImpl extends CommonRepositoryImpl<SysAdminUserMapper,SysAdminUser> implements SysAdminUserRepository<SysAdminUser> {


    @Override
    public SysAdminUser findByUserName(String userName){

        SysAdminUser sysAdminUser = this.getBaseMapper().findByUserName(userName);

        return sysAdminUser;
    }
}
