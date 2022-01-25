package com.starry.sky.infrastructure.utils.enums;

/**
 * @author wax
 * @description: 后台管理系统状态码
 * @date 2022-01-05
 */
public enum AdminResultCode implements IResultCode{

    INVALID_MENU_ONLY_KEY {
        @Override
        public String getMessage() {
            return "无效的菜单唯一键";
        }

        @Override
        public int getCode() {
            return 60000;
        }
    },
    USER_NOT_EXIST{
        @Override
        public String getMessage() {
            return "用户不存在";
        }

        @Override
        public int getCode() {
            return 60100;
        }
    },
    ACCOUNT_STATUS_FAIL{
        @Override
        public String getMessage() {
            return "账号被锁定或者注销";
        }

        @Override
        public int getCode() {
            return 60150;
        }
    },
    PARENT_ID_INVALID{
        @Override
        public String getMessage() {
            return "父ID不能是对象本身";
        }

        @Override
        public int getCode() {
            return 60200;
        }
    }



}
