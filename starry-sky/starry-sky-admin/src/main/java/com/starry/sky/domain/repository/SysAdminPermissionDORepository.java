package com.starry.sky.domain.repository;


import com.starry.sky.domain.entity.SysAdminPermissionDO;

import java.util.List;

public interface SysAdminPermissionDORepository{


    List<SysAdminPermissionDO> findAll();
}
