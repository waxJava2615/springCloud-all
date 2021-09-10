package com.starry.sky.infrastructure.filter;

import com.starry.sky.common.constant.CommonConstants;
import com.starry.sky.common.utils.ResUtils;
import com.starry.sky.common.utils.ThreadLocalHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-07
 */
public class CustomRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ResUtils resUtils = ThreadLocalHolder.getResUtils().get();
        resUtils.setRequest(request);
        resUtils.setResponse(response);
        request.setCharacterEncoding(CommonConstants.UTR_8);
        filterChain.doFilter(request,response);
    }
}
