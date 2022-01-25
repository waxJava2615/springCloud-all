package com.starry.sky.infrastructure.config.security;

import cn.hutool.core.convert.Convert;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.config.properties.ResourceServerProperties;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.utils.AuthConstants;
import com.starry.sky.interfaces.dubbo.ISourceRoleService;
import com.starry.sky.interfaces.vo.AdminRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-01
 */
@Slf4j
@Component
@RefreshScope
@EnableConfigurationProperties(ResourceServerProperties.class)
public class ResourceAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {


    @Autowired
    ResourceServerProperties resourceServerProperties;

    @DubboReference(version = "1.0.0",group = CommonConstants.DUBBO_SUB_STARRY_SKY_ADMIN,interfaceClass = ISourceRoleService.class,timeout =
            50000)
    ISourceRoleService iSourceRoleService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        if (request.getMethod() == HttpMethod.OPTIONS) { // 预检请求放行
            return Mono.just(new AuthorizationDecision(true));
        }

        //白名单路径直接放行 ignoreUrlsConfig.getUrls();
        List<String> ignoreUrls =resourceServerProperties.getIgnoreUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        //非管理端路径直接放行
        if (!pathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }

//        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
//        if (StringUtils.isNotBlank(token) && StringUtils.startsWithIgnoreCase(token,AuthConstants.JWT_TOKEN_PREFIX) ) {
//            if (pathMatcher.match(AuthConstants.APP_API_PREFIX, path)) {
//                // 移动端请求只需认证，无需后续鉴权
//                return Mono.just(new AuthorizationDecision(true));
//            }
//        } else {
//            return Mono.just(new AuthorizationDecision(false));
//        }

        // 获取权限
        Map<String, Object> urlPermRolesRules = createDefaultRolesMap();

        // 拥有访问权限的角色
        List<String> authorizedRoles = new ArrayList<>();

        // 是否需要鉴权，默认未设置拦截规则不需鉴权
        boolean requireCheck = false;

        for (Map.Entry<String, Object> permRoles : urlPermRolesRules.entrySet()) {
            String perm = permRoles.getKey();
            // 根据请求路径判断有访问权限的角色列表
            if (pathMatcher.match(perm, path)) {
                List<String> roles = Convert.toList(String.class, permRoles.getValue());
                authorizedRoles.addAll(Convert.toList(String.class, roles));
                if (!requireCheck) {
                    requireCheck = true;
                }
            }
        }
        if (!requireCheck) {
            return Mono.just(new AuthorizationDecision(true));
        }

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    if (AuthConstants.ROOT_ROLE_CODE.equalsIgnoreCase(authority)) {
                        // 如果是超级管理员则放行
                        return true;
                    }
                    return CollectionUtils.isNotEmpty(authorizedRoles) && authorizedRoles.contains(authority);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    private Map<String, Object> createDefaultRolesMap() {
        Map<String, Object> map = Maps.newHashMap();
        List<AdminRoleVO> list = iSourceRoleService.getSourceRole();
        if (list == null){
            list = Collections.emptyList();
        }
        list.forEach(v ->map.put(v.getUrl(),v.getRoleList()));
//        String[] arrAdmin = new String[]{"admin"};
//        String[] arrUser = new String[]{"user"};
//        String[] arrUserAdmin = new String[]{"user","admin"};
//        map.put("/dome/a/list",arrAdmin);
//        map.put("/dome/b/list",arrUser);
//        map.put("/dome/c/list",arrUserAdmin);
        return map;
    }
}
