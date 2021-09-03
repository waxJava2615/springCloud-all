package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储
 */
public interface SysAdminOperationRepository<T extends BaseEntity> extends BaseRepository<SysAdminOperation> {
    
    
    List<SysAdminOperation> findByOptionId(List<Long> listOperationId);
    
    
}
