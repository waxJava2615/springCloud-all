package com.starry.sky.infrastructure.oauth.extension.captcha;

import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.OauthConstants;
import com.starry.sky.infrastructure.utils.cache.ObjectCache;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wax
 * @description: 图形验证码
 * @date 2021-10-09
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "custom_admin";
    private final AuthenticationManager authenticationManager;

    private final ObjectCache objectCache;

    public CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
                               AuthenticationManager authenticationManager, ObjectCache objectCache) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.objectCache = objectCache;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
        String validateCode = parameters.get("validateCode");
        String uuid = parameters.get("uuid");
        if (StringUtils.isBlank(validateCode)){
            throw new CustomizeRuntimeException("验证码不能为空");
        }
        String validateCodeKey = OauthConstants.VALIDATE_CODE_PREFIX + uuid;
        // 缓存换取验证码
        String correctValidateCode = objectCache.get(validateCodeKey, String.class);
        if (!validateCode.equals(correctValidateCode)) {
            throw new CustomizeRuntimeException("验证码不正确");
        } else {
            objectCache.removeKey(validateCodeKey);
        }

        String username = parameters.get("username");
        String password = parameters.get("password");

        parameters.remove("password");
        parameters.remove("validateCode");
        parameters.remove("uuid");

        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
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
