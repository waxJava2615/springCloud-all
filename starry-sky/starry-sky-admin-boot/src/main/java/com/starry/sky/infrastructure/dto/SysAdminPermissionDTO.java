package com.starry.sky.infrastructure.dto;

import com.starry.sky.domain.entity.BaseDO;
import com.starry.sky.domain.entity.SysAdminMenuDO;
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
public class SysAdminPermissionDTO extends BaseDO {
    /**
     * 权限类型
     */
    private String type;


    List<SysAdminMenuDO> listSysAdminMenu = new ArrayList<>();

}