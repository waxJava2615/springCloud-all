package com.starry.sky.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wax
 * @description: 登录参数
 * @date 2021-11-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String username;

    private String password;

    private String code;

    private String refToken;

}
