package com.starry.sky.infrastructure.orm.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminPermissionMenuRelation  extends BaseEntity {
    /**
    * 权限ID
    */
    private Long permissionId;

    /**
    * 菜单ID
    */
    private Long menuId;

    private static final long serialVersionUID = 1L;
}