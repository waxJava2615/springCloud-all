package com.starry.sky.infrastructure.oauth.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.config.casthree.CustomOauthServerConfig;
import com.starry.sky.infrastructure.config.casthree.CustomWebSecurityConfig;
import com.starry.sky.infrastructure.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wax
 * @description: 主要处理AuthenticationException异常
 * @date 2021-10-22
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Autowired
    ObjectMapper objectMapper;

    private final RequestMatcher authorizationCodeGrantRequestMatcher = new AuthorizationCodeGrantRequestMatcher();

    private final AuthenticationEntryPoint loginUrlAuthenticationEntryPoint =
            new LoginUrlAuthenticationEntryPoint(CustomWebSecurityConfig.DEFAULT_LOGIN_URL);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.debug("Custom AuthenticationEntryPoint triggered with exception: {}.",
                authException.getClass().getCanonicalName());


        // 触发重定向到登陆页面
        if (authorizationCodeGrantRequestMatcher.matches(request)) {
            loginUrlAuthenticationEntryPoint.commence(request, response, authException);
            return;
        }
        ResultData.printJson(response, authException.getMessage());

    }


    private static class AuthorizationCodeGrantRequestMatcher implements RequestMatcher {

        /**
         * <ol>
         * <li>授权码模式 URI</li>
         * <li>隐式授权模式 URI</li>
         * </ol>
         */
        private static final Set<String> SUPPORT_URIS = new HashSet<>(Arrays.asList("response_type=code",
                "response_type=token"));

        @Override
        public boolean matches(HttpServletRequest request) {

            if (StringUtils.equals(request.getServletPath(), CustomOauthServerConfig.OAUTH_AUTHORIZE_ENDPOINT)) {
                final String queryString = request.getQueryString();
                return SUPPORT_URIS.stream().anyMatch(supportUri->StringUtils.indexOf(queryString, supportUri) != StringUtils.INDEX_NOT_FOUND);
            }

            return false;
        }
    }
}
