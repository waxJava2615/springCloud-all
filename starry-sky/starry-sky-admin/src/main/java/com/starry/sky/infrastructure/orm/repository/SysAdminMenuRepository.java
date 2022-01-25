package com.starry.sky.infrastructure.orm.repository;

import com.starry.sky.infrastructure.orm.po.BaseEntity;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import com.starry.sky.infrastructure.utils.Pager;

import java.util.List;

/**
 * @author wax
 * @description: 菜单仓储
 * @date 2021-08-20
 */
public interface SysAdminMenuRepository<T extends BaseEntity> extends BaseRepository<SysAdminMenu> {


    List<SysAdminMenu> findByMenuIdList(List<Long> listPermissionId);


    SysAdminMenu findByMenuId(long menuId);

    /**
     * 根据ID更新menu
     *
     * @param sysAdminMenu
     * @return
     */
    int updateByMenuId(SysAdminMenu sysAdminMenu);

    /**
     * 获取菜单列表
     * @param pageNum
     * @param pageSize
     * @param includeHidden
     * @return
     */
    List<SysAdminMenu> findListByHide(int pageNum,int pageSize,int includeHidden);

    /**
     *  根据（id 或者 父ID 或者 隐藏显示） 分页查询
     * @param pageNo
     * @param pageSize
     * @param hide
     * @param id
     * @param parentId
     * @param name
     * @return
     */
    Pager<SysAdminMenu> findMenuList(Integer pageNo, Integer pageSize, Integer hide, Long id,
                                     Long parentId, String name);

    /**
     * 根据唯一KEY查询
     * @param onlyKey
     * @return
     */
    SysAdminMenu findByOnlyKey(String onlyKey);


    int cusSave(SysAdminMenu sysAdminMenu);

}