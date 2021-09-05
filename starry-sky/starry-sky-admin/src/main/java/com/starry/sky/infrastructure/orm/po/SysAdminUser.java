package com.starry.sky.infrastructure.orm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName(value = "sys_admin_user")
public class SysAdminUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 加盐算法,盐值
     */
    @TableField(value = "password_salt")
    private String passwordSalt;

    /**
     * 0 正常用户  1 禁止登陆  2 注销用户
     */
    @TableField(value = "status")
    private Integer status;
    
    
    public static SysAdminUser generateDefault() {
        SysAdminUser sysAdminUser = new SysAdminUser();
        sysAdminUser.setId(Long.MAX_VALUE);
        return sysAdminUser;
    }
}