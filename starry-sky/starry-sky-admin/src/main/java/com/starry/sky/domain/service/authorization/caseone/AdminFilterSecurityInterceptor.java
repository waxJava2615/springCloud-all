package com.starry.sky.domain.service.authorization.caseone;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wax
 * @description: 自定义的权限验证过滤器
 * @date 2021-08-26
 */
public class AdminFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource adminFilterInvocationSecurityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            /**
             * 执行下一个拦截器
             */
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.adminFilterInvocationSecurityMetadataSource;
    }

    public void setAdminFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource adminFilterInvocationSecurityMetadataSource) {
        this.adminFilterInvocationSecurityMetadataSource = adminFilterInvocationSecurityMetadataSource;
    }
}
