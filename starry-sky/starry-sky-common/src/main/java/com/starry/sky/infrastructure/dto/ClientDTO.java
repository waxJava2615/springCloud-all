package com.starry.sky.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-22
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

        /**
         * 客户端 ID (主键)
         */
        private String id;

        /**
         * &#x5ba2;&#x6237;&#x7aef; Secret (&#x7ecf;&#x8fc7;
         * {@link org.springframework.security.crypto.password.PasswordEncoder#encode(CharSequence)} &#x52a0;&#x5bc6;
         * &#x7684;)
         */
        private String clientSecret;

        /**
         * 客户端 Scope (英文逗号分隔)
         */
        private String scope;

        /**
         * 授权方式. 可能的值有: authorization_code/implicit/password/client_credentials/refresh_token 的其中一种或多种 (英文逗号分隔)
         */
        private String authorizedGrantType;

        /**
         * 重定向地址, 当授权方式是 authorization_code 时有效. 如果有多个, 按英文逗号分隔.
         */
        private String redirectUri;

        /**
         * access-token 生命周期 (秒)
         */
        private Integer accessTokenValidity;

        /**
         * refresh-token 生命周期 (秒)
         */
        private Integer refreshTokenValidity;

        /**
         * 是否自动允许. 如果为 true, 则不需要用户手动允许
         */
        private boolean autoApprove;

        /**
         * 客户端描述
         */
        private String description;

        /**
         * 客户端可访问的资源 Id
         */
        private Set<String> resourceIds;

        /**
         * 客户端职权
         */
        private Set<String> authorities;

    }
