package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starry.sky.infrastructure.orm.base.SysAdminUserMapper;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import com.starry.sky.infrastructure.orm.repository.SysAdminUserRepository;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-23
 */

@Service
public class SysAdminUserRepositoryImpl extends BaseRepositoryImpl<SysAdminUserMapper, SysAdminUser> implements SysAdminUserRepository<SysAdminUser> {

    @Override
    public SysAdminUser findByUserName(String userName) {
        LambdaQueryWrapper<SysAdminUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysAdminUser::getUserName, userName);
        SysAdminUser sysAdminUser = this.getBaseMapper().selectOne(wrapper);
        return sysAdminUser;
    }

    @Override
    public SysAdminUser findByUserId(Long userId) {
        SysAdminUser sysAdminUser = this.getBaseMapper().selectById(userId);
        return sysAdminUser;
    }
}
