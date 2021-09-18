package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;

import java.util.List;

/**
 * @author wax
 * @description: 查询角色ORM
 * @date 2021-08-20
 */
public interface SysAdminRoleRepository<t extends BaseEntity> extends BaseRepository<SysAdminRole> {


    List<SysAdminRole> findList(int pageNum, int pageSize);

    List<SysAdminRole> findByIds(List<Long> listRoleId);

    /**
     * 获取角色对应的菜单
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SysAdminRole> findRolePermissionMenu(List<Long> listRoleId, int pageNum, int pageSize, int includeHide);

}