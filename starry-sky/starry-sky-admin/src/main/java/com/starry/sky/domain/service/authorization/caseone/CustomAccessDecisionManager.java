package com.starry.sky.domain.service.authorization.caseone;

import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.infrastructure.exception.CustomizeAccessDeniedException;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wax
 * @description: 自定义的权限控制管理器
 * @date 2021-08-26
 */
//@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判断是否有权限
     * @param authentication    包含了UserDetails用户信息
     * @param object            包含了request请求信息
     * @param configAttributes  由CustomFilterInvocationSecurityMetadataSource.getAttributes(object)返回的请求的资源（url）所需要的权限（角色）集合
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 未配置资源直接放行
        if (configAttributes == null || configAttributes.isEmpty()){
            return;
        }
        // 判断用户所拥有的权限是否是资源所需要的权限之一，如果是则放行，否则拦截
        configAttributes.stream().forEach(c -> {
            for (GrantedAuthority grantRole : authentication.getAuthorities()) {
                if (c.getAttribute().trim().equalsIgnoreCase(grantRole.getAuthority().trim())){
                    return;
                }
            }
        });


        throw new CustomizeAccessDeniedException(ResultCode.ACCESS_DENIED_NO_PERMISSION.getCode(),ResultCode.ACCESS_DENIED_NO_PERMISSION.getMessage());


    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
