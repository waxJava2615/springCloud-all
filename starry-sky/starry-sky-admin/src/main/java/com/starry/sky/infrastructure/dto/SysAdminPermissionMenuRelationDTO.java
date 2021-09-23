package com.starry.sky.infrastructure.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 权限菜单参数
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysAdminPermissionMenuRelationDTO extends SysAdminDTO{

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 菜单ID
     */
    private Long menuId;


    private SysAdminOperationDTO sysAdminOperationDTO;

    private SysAdminMenuDTO sysAdminMenuDTO;

    private List<Long> listOtherId = new ArrayList<>();

}
