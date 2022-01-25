package com.starry.sky.infrastructure.utils.enums;

/**
 * @author wax
 * @description: 授权异常信息返回定义
 * @date 2022-01-05
 */
public enum OauthResultCode implements IResultCode{

    OAUTH_CLIENT_ID_INVALID{
        @Override
        public String getMessage() {
            return "无效的客户端ID";
        }

        @Override
        public int getCode() {
            return 40100;
        }
    },
    CAPTCHA_ERROR {
        @Override
        public String getMessage() {
            return "验证码不正确";
        }

        @Override
        public int getCode() {
            return 50000;
        }
    },

    CLIENT_SECRET_INVALID{
        @Override
        public String getMessage() {
            return "client secret无效";
        }

        @Override
        public int getCode() {
            return 50010;
        }
    },

    CAPTCHA_NULL{
        @Override
        public String getMessage() {
            return "验证码不能为空";
        }

        @Override
        public int getCode() {
            return 50020;
        }
    },
    CAPTCHA_INVALID{
        @Override
        public String getMessage() {
            return "验证码无效";
        }

        @Override
        public int getCode() {
            return 50030;
        }
    },
    AUTHENTICATION_NOT_USER{
        @Override
        public String getMessage() {
            return "用户不存在";
        }

        @Override
        public int getCode() {
            return 20100;
        }
    }

}
