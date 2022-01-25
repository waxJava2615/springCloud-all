package com.starry.sky.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: 用户DTO
 * @date 2021-11-16
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminUserDTO extends BaseDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户图像
     */
    private String logo;

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
     * 0 正常用户  1 锁定用户  2 注销用户
     */
    private Integer status;


    private List<String> authorities;


    private List<AdminMenuDTO> listMenu;


}
