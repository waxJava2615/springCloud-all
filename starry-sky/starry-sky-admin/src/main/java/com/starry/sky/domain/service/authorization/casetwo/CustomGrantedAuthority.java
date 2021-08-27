package com.starry.sky.domain.service.authorization.casetwo;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author wax
 * @description: 实现自定义权限
 * @date 2021-08-27
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private final String auth;

    public CustomGrantedAuthority(String auth) {
        this.auth = auth;
    }


    @Override
    public String getAuthority() {
        return auth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CustomGrantedAuthority) {
            return auth.equals(((CustomGrantedAuthority) obj).auth);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.auth.hashCode();
    }

    @Override
    public String toString() {
        return this.auth;
    }


}
