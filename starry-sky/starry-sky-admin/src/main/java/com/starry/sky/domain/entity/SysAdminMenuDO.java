package com.starry.sky.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminMenuDO extends BaseDO {
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;


    private String onlyKey;

    /**
     * 菜单父ID
     */
    private Long parentId;

    // 从父节点到前节点的上一级
    private SysAdminMenuDO parentMenu;

    private SysAdminMenuDO childrenMenu;
    /**
     * 位置 左边  或者 头部
     */
    private int option;

    /**
     * 样式或者图片
     */
    private String icon;

    /**
     * 地址映射的权限集合
     */
    private Collection<String> authorities;


    /**
     * 修改菜单
     * @param name
     * @param url
     * @param onlyKey
     * @param parentId
     * @param option
     * @param icon
     * @param hide
     * @param orderKey
     * @param sysAdminUserId
     */
    public void editFiled(String name,String url,String onlyKey,Long parentId,int option, String icon,Integer hide,
                          Long orderKey,Long sysAdminUserId) {
        this.name = name;
        this.url = url;
        this.onlyKey = onlyKey;
        this.parentId = parentId;
        this.option = option;
        this.icon = icon;
        this.setHide(hide);
        this.setOrder(orderKey == null? 0 : orderKey);
        this.setUpdateTime(new Date());
        this.setUpdatedBy(sysAdminUserId);
    }

    public void pushFiled(Long sysAdminUserId){
        Date date = new Date();
        this.setUpdateTime(date);
        this.setCreateTime(date);
        this.setUpdatedBy(sysAdminUserId);
    }
}