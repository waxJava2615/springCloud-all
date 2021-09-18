package com.starry.sky.infrastructure.dto;

import lombok.*;

/**
 * @author wax
 * @description: 用户角色关联
 * @date 2021-09-08
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysAdminUserRoleDTO extends SysAdminDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
