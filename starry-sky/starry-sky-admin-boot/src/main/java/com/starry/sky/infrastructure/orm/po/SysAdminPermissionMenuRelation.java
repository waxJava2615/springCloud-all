package com.starry.sky.infrastructure.orm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_admin_permission_menu_relation")
public class SysAdminPermissionMenuRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 权限ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;


    @TableField(exist = false)
    private SysAdminPermission sysAdminPermission;

    @TableField(exist = false)
    private SysAdminMenu sysAdminMenu;

}