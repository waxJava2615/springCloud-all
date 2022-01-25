package com.starry.sky.application.service;

import com.starry.sky.application.dto.AdminRoleDTO;
import com.starry.sky.infrastructure.dto.SysAdminRoleDTO;

import java.util.List;

/**
 * @author wax
 * @description: 资源 权限获取
 * @date 2021-11-15
 */
public interface SourceRoleAppService {


    /**
     * 获取所有的资源 以及 操作的权限映射
     * @param sysAdminRoleDTO
     * @return
     */
    List<AdminRoleDTO> getSourceOperationRole(SysAdminRoleDTO sysAdminRoleDTO);


}
