package com.starry.sky.infrastructure.dto;

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
public class SysAdminUserDTO extends SysAdminDTO{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐算法,盐值
     */
    private String passwordSalt;

    /**
     * 0 正常用户  1 禁止登陆  2 注销用户
     */
    private Integer status;

}
