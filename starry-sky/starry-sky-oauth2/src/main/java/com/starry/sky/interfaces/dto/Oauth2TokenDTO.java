package com.starry.sky.interfaces.dto;

import lombok.*;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oauth2TokenDTO {

    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;

}
