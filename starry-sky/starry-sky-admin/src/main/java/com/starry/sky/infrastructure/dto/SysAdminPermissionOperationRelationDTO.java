package com.starry.sky.infrastructure.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 权限操作
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAdminPermissionOperationRelationDTO extends SysAdminDTO{
    

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 操作ID
     */
    private Long operationId;


    private List<Long> listOtherId = new ArrayList<>();


}
