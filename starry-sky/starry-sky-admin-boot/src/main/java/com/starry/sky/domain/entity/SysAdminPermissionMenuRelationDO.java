package com.starry.sky.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminPermissionMenuRelationDO extends BaseDO {
    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 菜单ID
     */
    private Long menuId;

}