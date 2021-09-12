package com.starry.sky.infrastructure.param;

import lombok.*;

/**
 * @author wax
 * @description: 用户参数
 * @date 2021-09-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAdminUserParam extends SysAdminParam{

    private String userName;

}
