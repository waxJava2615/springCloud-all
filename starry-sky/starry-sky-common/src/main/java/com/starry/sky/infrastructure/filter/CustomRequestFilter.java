package com.starry.sky.infrastructure.filter;

import cn.hutool.core.convert.Convert;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.handler.RequestParameterWrapper;
import com.starry.sky.infrastructure.utils.ResUtils;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wax
 * @description: 请求编码过滤
 * @date 2021-09-07
 */
@Slf4j
public class CustomRequestFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
//            HttpServletRequest request = (HttpServletRequest)servletRequest;
//            HttpServletResponse response = (HttpServletResponse)servletResponse;
            ResUtils resUtils = ThreadLocalHolder.getResUtils().get();
            Integer pageNo = Convert.convert(Integer.class, request.getParameter("pageNo"),1);
            Integer pageSize = Convert.convert(Integer.class, request.getParameter("pageSize"),10);
            Map<String, Object> map = new HashMap<>();
            map.put("pageNo", pageNo);
            map.put("pageSize", pageSize);
            map.put("pageNum", new String[]{pageNo + ""});
            RequestParameterWrapper wrapper = new RequestParameterWrapper(request,map);
            wrapper.setCharacterEncoding(CommonConstants.UTF_8.name());
            resUtils.setRequest(wrapper);
            resUtils.setResponse(response);
            filterChain.doFilter(wrapper, response);
        } catch (Exception e) {
            log.error("customRequestFilter Exception:", e);
            throw e;
        } finally {
            ThreadLocalHolder.removeAll();
        }
    }
}
