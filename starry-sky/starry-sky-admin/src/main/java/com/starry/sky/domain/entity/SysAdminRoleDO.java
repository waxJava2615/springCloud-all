package com.starry.sky.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminRoleDO extends BaseDO {
    /**
     * 角色名称
     */
    private String name;


    List<SysAdminPermissionDO> listSysAdminPermission = new ArrayList<>();
}