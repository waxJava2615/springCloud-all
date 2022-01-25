package com.starry.sky.infrastructure.orm.repository;


import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminPermission;

import java.util.List;

public interface SysAdminPermissionRepository<T extends BaseEntity> extends BaseRepository<SysAdminPermission> {


    List<SysAdminPermission> findAll();
}
