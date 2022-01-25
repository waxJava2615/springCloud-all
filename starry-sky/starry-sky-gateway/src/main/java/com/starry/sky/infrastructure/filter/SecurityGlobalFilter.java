package com.starry.sky.infrastructure.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import com.starry.sky.infrastructure.utils.AuthConstants;
import com.starry.sky.infrastructure.utils.enums.GateWayResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;

/**
 * @author wax
 * @description: 安全拦截全局过滤器
 * @date 2021-11-05
 */
@Slf4j
@Component
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    public SecurityGlobalFilter(){}

    @Autowired
    private RSAPublicKey rsaPublicKey;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token) ||  !StrUtil.startWithIgnoreCase(token,AuthConstants.JWT_TOKEN_PREFIX)) {
            return chain.filter(exchange);
        }
        // 去除前缀后 如果token 空 直接异常 由 CustomErrorWebExceptionHandler#getErrorAttributes 输出JSON
        token = StringUtils.replace(token, AuthConstants.JWT_TOKEN_PREFIX, "");
        if (StrUtil.isBlank(token)){
            throw new CustomizeRuntimeException(GateWayResultCode.TOKEN_NOT_NUll);
        }

        JWSObject jwsObject = JWSObject.parse(token);
        String payload = StrUtil.toString(jwsObject.getPayload());

        request = exchange.getRequest().mutate()
                .header(AuthConstants.JWT_TOKEN_PAYLOAD, URLEncoder.encode(payload, "UTF-8"))
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
