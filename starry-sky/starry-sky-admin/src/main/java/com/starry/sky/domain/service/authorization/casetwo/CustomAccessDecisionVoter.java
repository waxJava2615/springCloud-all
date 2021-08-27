package com.starry.sky.domain.service.authorization.casetwo;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wax
 * @description: 自定义投票器
 * @date 2021-08-27
 */
public class CustomAccessDecisionVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null) && attribute instanceof CustomGrantedAuthority){
            return true;
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        for (ConfigAttribute attribute : attributes) {
            // 默认的SpringSecurity的投票器，比如RoleVoter 的 support方法会去判断角色是否 包含ROLE_前缀
            // 这里不做这种限制
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;
                for (GrantedAuthority authority : userAuthorities) {
                    if(attribute.getAttribute().equals(authority.getAuthority())){
                        return ACCESS_GRANTED;
                    }
                }
            }
        }
        return result;
    }
}
