package com.starry.sky.infrastructure.param;

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
@Builder
public class SysAdminPermissionMenuRelationParam extends SysAdminParam{

    private Long permissionId;
    
    
    private Long menuId;
    
    
    private List<Long> listOtherId = new ArrayList<>();



}
