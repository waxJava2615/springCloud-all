package com.starry.sky.infrastructure.dto;

import com.starry.sky.domain.entity.SysAdminPermissionDO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: 角色参数类
 * @date 2021-09-10
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysAdminRoleDTO extends SysAdminDTO {


    /**
     * 角色名称
     */
    private String name;

    private List<SysAdminPermissionDO> listSysAdminPermission = new ArrayList<>();

    //查询使用
    private List<Long> listRoleId = new ArrayList<>();

    private List<Long> listOperationId = new ArrayList<>();

    private List<String> listNames = new ArrayList<>();;
}
