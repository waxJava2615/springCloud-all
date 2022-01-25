package com.starry.sky.infrastructure.utils;

import lombok.Getter;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-11
 */
@Getter
public enum AccountTypeEnum {

    USERNAME("password","密码模式"),
    MOBILE("mobile","手机号");

    private String type;
    private String name;
    AccountTypeEnum(String type,String name){
        this.type = type;
        this.name = name;
    }


    public static AccountTypeEnum getByType(String type){
        AccountTypeEnum accountTypeEnum = null;
        for (AccountTypeEnum item : values()) {
            if (item.getType().equals(type)) {
                accountTypeEnum = item;
                continue;
            }
        }
        return accountTypeEnum;
    }

}
