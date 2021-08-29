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
public class SysAdminRolePermissionRelation  extends BaseEntity {
    /**
    * 角色ID
    */
    private Long roleId;

    /**
    * 权限ID
    */
    private Long permissionId;

    private static final long serialVersionUID = 1L;
}