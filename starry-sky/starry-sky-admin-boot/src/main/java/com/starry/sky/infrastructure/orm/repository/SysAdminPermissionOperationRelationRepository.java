package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 权限操作仓储
 */
public interface SysAdminPermissionOperationRelationRepository<T extends BaseEntity> extends BaseRepository<SysAdminPermissionOperationRelation> {


    List<SysAdminPermissionOperationRelation> findByPermissionId(List<Long> listPermissionId);
}
