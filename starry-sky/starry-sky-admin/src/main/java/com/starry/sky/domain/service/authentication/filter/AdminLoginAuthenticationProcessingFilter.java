package com.starry.sky.domain.service.authentication.filter;

import com.starry.sky.common.constant.StarrySkyAdminConstants;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.domain.service.authentication.provider.AdminLoginAuthenticationToken;
import com.starry.sky.infrastructure.exception.CustomizeAuthenticationException;
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
public class AdminLoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {


    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "account";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";


    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
    private boolean postOnly = true;

    public AdminLoginAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher(StarrySkyAdminConstants.LOGIN_PRECESS_URL,
                StarrySkyAdminConstants.LOGIN_PRECESS_HTTP_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equalsIgnoreCase(StarrySkyAdminConstants.LOGIN_PRECESS_HTTP_METHOD)) {
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_SUPPORT_POST.getCode(),
                    ResultCode.AUTHENTICATION_SUPPORT_POST.getMessage());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        // 添加验证码等等操作  需要传入
        String verificationCode = obtainImgCode(request);


        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        // 后续移动到AUTHOR项目中可以添加一个TYPE  来判断登录类型  创建不同的 AuthenticationToken
        Authentication authRequest = new AdminLoginAuthenticationToken(username, password, verificationCode);

        // Allow subclasses to set the "details" property
        setDetails(request, (AdminLoginAuthenticationToken) authRequest);

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


    protected void setDetails(HttpServletRequest request, AdminLoginAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
