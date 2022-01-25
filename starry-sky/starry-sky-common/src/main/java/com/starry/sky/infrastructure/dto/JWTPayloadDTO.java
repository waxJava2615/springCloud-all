package com.starry.sky.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wax
 * @description: jwt解析或的实体映射
 * @date 2021-11-24
 */
@Getter
@Setter
public class JWTPayloadDTO {

    public JWTPayloadDTO(){}

    private String clientId;

    @JsonAlias({"user_name"})
    private String userName;

    private Long id;

    private String jti;

    private List<String> authorities;

}
