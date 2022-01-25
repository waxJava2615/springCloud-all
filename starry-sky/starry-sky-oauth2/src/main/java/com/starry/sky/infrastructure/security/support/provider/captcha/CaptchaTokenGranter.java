package com.starry.sky.infrastructure.security.support.provider.captcha;

import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.enums.OauthResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-29
 */
public class CaptchaTokenGranter extends AbstractTokenGranter{

    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;

    public CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory, AuthenticationManager authenticationManager,
                               StringRedisTemplate redisTemplate
    ) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());

        String validateCode = parameters.get("code");

        if (StringUtils.isBlank(validateCode)){
            throw new CustomizeRuntimeException(OauthResultCode.CAPTCHA_NULL);
        }
        if (!validateCode.equals("6666")) {
            throw new CustomizeRuntimeException(OauthResultCode.CAPTCHA_ERROR);
        } else {
            // 删除缓存中的KEY
        }

        String username = parameters.get("username");
        String password = parameters.get("password");

        parameters.remove("password");
        parameters.remove("validateCode");
        parameters.remove("uuid");

        Authentication userAuth = new CustomUsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException var8) {
            throw new InvalidGrantException(var8.getMessage());
        } catch (BadCredentialsException var9) {
            throw new InvalidGrantException(var9.getMessage());
        }

        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
    }

}
