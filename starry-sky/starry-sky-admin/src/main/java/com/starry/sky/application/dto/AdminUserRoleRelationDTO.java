package com.starry.sky.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-15
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminUserRoleRelationDTO extends BaseDTO{

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
