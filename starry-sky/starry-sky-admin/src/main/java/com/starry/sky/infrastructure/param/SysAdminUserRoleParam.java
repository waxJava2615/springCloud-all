package com.starry.sky.infrastructure.param;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wax
 * @description: 用户角色关联
 * @date 2021-09-08
 */
@Getter
@Setter
@Builder
public class SysAdminUserRoleParam extends SysAdminParam {

    private Long userId;


    private Long roleId;

}
