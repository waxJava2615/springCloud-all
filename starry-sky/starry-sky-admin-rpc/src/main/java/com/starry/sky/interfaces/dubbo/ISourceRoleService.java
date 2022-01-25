package com.starry.sky.interfaces.dubbo;

import com.starry.sky.interfaces.vo.AdminRoleVO;

import java.util.List;

/**
 * @author wax
 * @description: 资源权限映射
 * @date 2021-11-10
 */
public interface ISourceRoleService {

    /**
     * 获取资源和操作对应的权限集合
     * @return
     */
    List<AdminRoleVO> getSourceRole();

}
