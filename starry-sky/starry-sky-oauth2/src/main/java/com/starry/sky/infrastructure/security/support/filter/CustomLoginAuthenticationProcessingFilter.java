package com.starry.sky.infrastructure.security.support.filter;

import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.security.support.provider.captcha.CustomUsernamePasswordAuthenticationToken;
import com.starry.sky.infrastructure.utils.constants.OauthConstants;
import com.starry.sky.infrastructure.utils.enums.ResultCode;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description
 */
public class CustomLoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String FORM_LOGIN_TYPE_KEY = "loginType";
    public static final String FORM_ACCOUNT_KEY = "account";
    public static final String FORM_PASSWORD_KEY = "password";
    public static final String FORM_CODE_KEY = "code";


    private String usernameParameter = FORM_ACCOUNT_KEY;
    private String passwordParameter = FORM_PASSWORD_KEY;
    private String codeParameter = FORM_CODE_KEY;
    private String loginTypeParameter = FORM_LOGIN_TYPE_KEY;
    private boolean postOnly = true;

    public CustomLoginAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher(OauthConstants.LOGIN_PRECESS_URL,
                OauthConstants.LOGIN_PRECESS_HTTP_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equalsIgnoreCase(OauthConstants.LOGIN_PRECESS_HTTP_METHOD)) {
            throw new CustomizeRuntimeException(ResultCode.REQUEST_SUPPORT_POST);
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        // 添加验证码等等操作  需要传入
        String verificationCode = obtainImgCode(request);
        String loginType = obtainLoginType(request);

        Authentication authRequest = null;
        if (OauthConstants.LONG_TYPE_SMS.equals(loginType)){

        }else{
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }
            username = username.trim();
            authRequest = new CustomUsernamePasswordAuthenticationToken(username, password);
            setDetails(request, (CustomUsernamePasswordAuthenticationToken) authRequest);
        }

        return this.getAuthenticationManager().authenticate(authRequest);

    }


    @Nullable
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    @Nullable
    protected String obtainImgCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    protected String obtainLoginType(HttpServletRequest request) {
        return request.getParameter(loginTypeParameter);
    }

    protected void setDetails(HttpServletRequest request, CustomUsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
