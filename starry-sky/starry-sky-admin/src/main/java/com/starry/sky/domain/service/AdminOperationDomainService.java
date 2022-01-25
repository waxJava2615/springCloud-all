package com.starry.sky.domain.service;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.infrastructure.dto.SysAdminOperationDTO;

import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: 菜单
 * @date 2021-11-15
 */
public interface AdminOperationDomainService {


    /**
     * 获取操作
     * @param sysAdminOperationDTO #hide
     * @return
     */

    List<SysAdminOperationDO> getOperation(SysAdminOperationDTO sysAdminOperationDTO);



    /**
     * 根据模块 和当前登录用户获取权限 获取操作
     */
    Map<String,SysAdminOperationDO> loadModuleOperation();

}
