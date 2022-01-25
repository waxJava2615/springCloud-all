package com.starry.sky.interfaces.facade;

import com.starry.sky.infrastructure.utils.ResultCode;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.interfaces.dto.Oauth2TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author wax
 * @description: 自定义登录路径
 * @date 2021-10-21
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token1")
    public ResultData postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto =
                Oauth2TokenDto.builder().token(oAuth2AccessToken.getValue()).refreshToken(oAuth2AccessToken.getRefreshToken().getValue()).expiresIn(oAuth2AccessToken.getExpiresIn()).tokenHead("Bearer ").build();
        return ResultData.customizeResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                oauth2TokenDto);
    }

}
