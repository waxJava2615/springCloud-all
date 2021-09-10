package com.starry.sky.infrastructure.param;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wax
 * @description: 用户参数
 * @date 2021-09-09
 */
@Getter
@Setter
@NoArgsConstructor
public class SysAdminUserParam extends SysAdminParam{

    private String userName;

}
