package com.starry.sky.infrastructure.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 角色权限关联
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAdminRolePermissionRelationDTO extends SysAdminDTO{

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;
    
    
    private List<Long> listRoleId = new ArrayList<>();
    
}
