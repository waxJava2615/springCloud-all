package com.starry.sky.infrastructure.orm.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

  /**  
    * @description: TODO
    * @author wax
    * @date 2021-08-20
    */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysAdminUser extends BaseEntity {
    /**
    * 后台用户ID
    */
    private Long id;

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

    private static final long serialVersionUID = 1L;
}