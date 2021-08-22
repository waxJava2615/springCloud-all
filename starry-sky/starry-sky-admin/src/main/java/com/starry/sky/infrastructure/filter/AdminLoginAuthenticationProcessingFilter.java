package com.starry.sky.infrastructure.filter;

import com.starry.sky.common.exception.CustomizeAuthenticationException;
import com.starry.sky.common.utils.ResultCode;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
    public static final String SPRING_SECURITY_FORM_IMGCODE_KEY = "imgCode";


    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String imgCodeParameter = SPRING_SECURITY_FORM_IMGCODE_KEY;
    private boolean postOnly = true;


    /**
     * @param defaultFilterProcessesUrl the default value for <tt>filterProcessesUrl</tt>.
     */
    protected AdminLoginAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher("/admin/login", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equalsIgnoreCase("post")) {
            throw new CustomizeAuthenticationException(ResultCode.AUTHENTICATION_SUPPORT_POST.getMessage(),
                    ResultCode.AUTHENTICATION_SUPPORT_POST.getCode());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        // TODO 添加验证码等等操作
        String verificationCode = obtainImgCode(request);


        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

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
    protected String  obtainImgCode(HttpServletRequest request){
        return request.getParameter(imgCodeParameter);
    }


    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
