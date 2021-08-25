package com.starry.sky.domain.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.sky.domain.repository.SysAdminMenuRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
@Service
public class SysAdminMenuRepositoryImpl extends ServiceImpl<SysAdminMenuMapper, SysAdminMenu> implements SysAdminMenuRepository {

    @Resource
    private SysAdminMenuMapper sysAdminMenuMapper;





}