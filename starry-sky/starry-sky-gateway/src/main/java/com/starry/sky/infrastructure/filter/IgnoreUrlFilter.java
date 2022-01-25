package com.starry.sky.infrastructure.filter;

import com.starry.sky.infrastructure.config.properties.ResourceServerProperties;
import com.starry.sky.infrastructure.utils.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * 忽略地址拦截器
 * @author wax
 * @description: 此处删除请求头  使请求不进入SecurityGlobalFilter
 * @date 2021-11-22
 */
@Slf4j
//@Component
//@RefreshScope
//@EnableConfigurationProperties(ResourceServerProperties.class)
public class IgnoreUrlFilter implements WebFilter {

    public IgnoreUrlFilter(){}


    @Autowired
    private ResourceServerProperties resourceServerProperties;

    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link WebFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = resourceServerProperties.getIgnoreUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstants.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
