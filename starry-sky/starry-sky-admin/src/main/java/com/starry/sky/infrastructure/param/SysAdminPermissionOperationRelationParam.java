package com.starry.sky.infrastructure.param;

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
public class SysAdminPermissionOperationRelationParam  extends SysAdminParam{
    
    
    private Long permissionId;
    
    
    private Long optionId;
    
    
    private List<Long> listOtherId = new ArrayList<>();



}
