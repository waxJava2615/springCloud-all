package com.starry.sky.infrastructure.filter;

import com.starry.sky.common.constant.CommonConstants;
import com.starry.sky.common.utils.ResUtils;
import com.starry.sky.common.utils.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: 请求编码过滤
 * @date 2021-09-07
 */
@Slf4j
public class CustomRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            ResUtils resUtils = ThreadLocalHolder.getResUtils().get();
            request.setAttribute("wax","sky");
            resUtils.setRequest(request);
            resUtils.setResponse(response);
            request.setCharacterEncoding(CommonConstants.UTF_8.name());
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            log.error("customRequestFilter Exception:",e);
            throw e;
        } finally {
            ThreadLocalHolder.removeAll();
        }
    }
}
