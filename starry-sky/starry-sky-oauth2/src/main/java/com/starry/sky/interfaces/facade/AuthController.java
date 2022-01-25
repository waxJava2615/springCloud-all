package com.starry.sky.interfaces.facade;

import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.utils.constants.OauthConstants;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import com.starry.sky.interfaces.dto.Oauth2TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.Principal;
import java.util.Map;

/**
 * @author wax
 * @description: 自定义请求Auth
 * @date 2021-10-27
 */

@RestController
public class AuthController {


    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    KeyPair keyPair;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(OauthConstants.CUSTOM_TOKEN_URL)
    public ResultData postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDTO oauth2TokenDTO =
                Oauth2TokenDTO.builder().token(oAuth2AccessToken.getValue()).refreshToken(oAuth2AccessToken.getRefreshToken().getValue()).expiresIn(oAuth2AccessToken.getExpiresIn()).tokenHead("Bearer ").build();
        return ResultData.customizeResult(ResultCode.SUCCESS, oauth2TokenDTO);
    }


}
