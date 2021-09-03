package com.starry.sky.domain.repository.impl;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.domain.repository.SysAdminMenuDORepository;
import com.starry.sky.infrastructure.orm.repository.SysAdminMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
  * @description: 菜单仓储
  * @author wax
  * @date 2021-08-20
  */
@Service
public class SysAdminMenuDORepositoryImpl implements SysAdminMenuDORepository {
    

    @Autowired
    SysAdminMenuRepository sysAdminMenuRepository;



    @Override
    public List<SysAdminMenuDO> findByMenuId(List<Long> listMenuId) {

        return sysAdminMenuRepository.findByMenuId(listMenuId);
    }
}