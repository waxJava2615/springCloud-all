package com.starry.sky.infrastructure.config.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starry.sky.infrastructure.config.properties.ResourceServerProperties;
import com.starry.sky.infrastructure.utils.AuthConstants;
import com.starry.sky.infrastructure.utils.ResponseUtils;
import com.starry.sky.infrastructure.utils.ResultBase;
import com.starry.sky.infrastructure.utils.ResultData;
import com.starry.sky.infrastructure.utils.enums.GateWayResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author wax
 * @description: 资源配置类
 * @date 2021-11-01
 */
@Slf4j
@EnableConfigurationProperties(ResourceServerProperties.class)
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    @Qualifier("jacksonObjectMapper")
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceAuthorizationManager resourceAuthorizationManager;


    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
                .publicKey(rsaPublicKey()) // 本地获取公钥
        //.jwkSetUri() // 远程获取公钥
        ;
        http.oauth2ResourceServer()
                // 处理未授权
                .accessDeniedHandler(accessDeniedHandler())
                //处理未认证
                .authenticationEntryPoint(authenticationEntryPoint());
        http.authorizeExchange()
                .pathMatchers(Convert.toStrArray(resourceServerProperties.getIgnoreUrls())).permitAll()
                .anyExchange().access(resourceAuthorizationManager)
                .and()
                .exceptionHandling()
                .and().csrf().disable();

        return http.build();
    }

    /**
     * 自定义未授权响应
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) ->Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    ResultBase resultData = ResultData.customizeResult(GateWayResultCode.AUTHENTICATION_UNAUTHORIZED);
                    String json = null;
                    try {
                        json = objectMapper.writeValueAsString(resultData);
                    } catch (JsonProcessingException e) {
                        log.error("JsonProcessingException error:\t",e);
                    }
                    return ResponseUtils.printJson(response,json);
                });
    }

    /**
     * token无效或者已过期自定义响应
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) ->Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    ResultData resultData = ResultData.customizeResult(GateWayResultCode.TOKEN_EXPIRED_OR_INVALID);
//                    ResultData resultData = new ResultData<String>();
//                    resultData.setMsg(ResultCode.TOKEN_EXPIRED_OR_INVALID.getMessage());
//                    resultData.setCode(ResultCode.TOKEN_EXPIRED_OR_INVALID.getCode());
                    String json = null;
                    try {
                        json = objectMapper.writeValueAsString(resultData);
                    } catch (JsonProcessingException ex) {
                        log.error("JsonProcessingException error:\t",ex);
                    }
                    return ResponseUtils.printJson(response,json);
                });
    }

    /**
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.JWT_AUTHORITIES_KEY);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    /**
     * 本地获取JWT验签公钥
     */
    @SneakyThrows
    @Bean
    public RSAPublicKey rsaPublicKey() {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String publicKeyData = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(publicKeyData)));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPublicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        return rsaPublicKey;
    }


}
