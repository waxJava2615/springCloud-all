package com.starry.sky.infrastructure.filter;

import com.starry.sky.common.constant.CommonConstants;
import com.starry.sky.common.utils.ResUtils;
import com.starry.sky.common.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.handler.RequestParameterWrapper;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            request.setCharacterEncoding(CommonConstants.UTF_8.name());
            ResUtils resUtils = ThreadLocalHolder.getResUtils().get();
            resUtils.setRequest(request);
            resUtils.setResponse(response);
            RequestParameterWrapper wrapper = new RequestParameterWrapper(request);
            int pageNum = resUtils.getIntParam("pageNum");
            int pageSize = resUtils.getIntParam("pageSize");
            if (pageNum <= 0 && pageSize<= 0){
                Map<String, Object> map = new HashMap<>();
                map.put("pageNum", new String[]{"1"});
                map.put("pageSize", new String[]{"10"});
                wrapper.addParameters(map);
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            log.error("customRequestFilter Exception:",e);
            throw e;
        } finally {
            ThreadLocalHolder.removeAll();
        }
    }
}
