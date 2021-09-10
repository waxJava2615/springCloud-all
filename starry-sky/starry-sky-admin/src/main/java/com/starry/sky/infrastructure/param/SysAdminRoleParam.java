package com.starry.sky.infrastructure.param;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 角色参数类
 * @date 2021-09-10
 */


@Getter
@Setter
@NoArgsConstructor
@Builder
public class SysAdminRoleParam extends SysAdminParam {

    private String name;




    //查询使用
    private List<Long> listRoleId;

}
