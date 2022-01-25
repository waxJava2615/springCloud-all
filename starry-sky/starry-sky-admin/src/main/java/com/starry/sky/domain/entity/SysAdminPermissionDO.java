package com.starry.sky.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminPermissionDO extends BaseDO {
    /**
     * 权限类型
     */
    private String type;


    /**
     * 权限对应的菜单资源
     */
    List<SysAdminMenuDO> listSysAdminMenu = new ArrayList<>();

    /**
     * 权限对应的操作
     */
    List<SysAdminOperationDO> listSysAdminOperation = new ArrayList<>();

}