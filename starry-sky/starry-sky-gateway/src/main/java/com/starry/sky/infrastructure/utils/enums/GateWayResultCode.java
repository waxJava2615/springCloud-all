package com.starry.sky.infrastructure.utils.enums;

/**
 * @author wax
 * @description: 网关状态，码
 * @date 2022-01-05
 */
public enum GateWayResultCode implements IResultCode{
    TOKEN_NOT_NUll{
        @Override
        public String getMessage() {
            return "token不能为空";
        }

        @Override
        public int getCode() {
            return 30100;
        }
    },
    AUTHENTICATION_UNAUTHORIZED{
        @Override
        public String getMessage() {
            return "未授权,无法访问";
        }

        @Override
        public int getCode() {
            return 20400;
        }
    },
    TOKEN_EXPIRED_OR_INVALID {
        @Override
        public String getMessage() {
            return "token过期或无效";
        }

        @Override
        public int getCode() {
            return 30000;
        }
    }
}
