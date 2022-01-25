package com.starry.sky.domain.entity;

import com.starry.sky.infrastructure.constant.AdminConstant;
import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.enums.AdminResultCode;
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
public class SysAdminUserDO extends BaseDO {
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

    private String logo;


    /**
     * 判断账号是否正常
     */
    public void checkStatus(){
        if (this.getStatus() != AdminConstant.ACCOUNT_DEFAULT){
            throw new CustomizeRuntimeException(AdminResultCode.ACCOUNT_STATUS_FAIL);
        }
    }


}