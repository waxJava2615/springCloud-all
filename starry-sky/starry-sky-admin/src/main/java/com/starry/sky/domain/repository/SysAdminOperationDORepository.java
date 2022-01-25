package com.starry.sky.domain.repository;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/29
 * @description 操作仓储
 */
public interface SysAdminOperationDORepository {


    List<SysAdminOperationDO> findByOptionId(SysAdminOperationDTO sysAdminOperationDTO);


    /**
     * 获取操作集合
     * @param sysAdminOperationDTO
     * @return
     */
    List<SysAdminOperationDO> findByHide(SysAdminOperationDTO sysAdminOperationDTO);


    /**
     * 根据父id获取操作列表
     * @param sysAdminOperationDTO
     * @return
     */
    List<SysAdminOperationDO> findByParentId(SysAdminOperationDTO sysAdminOperationDTO);

}
