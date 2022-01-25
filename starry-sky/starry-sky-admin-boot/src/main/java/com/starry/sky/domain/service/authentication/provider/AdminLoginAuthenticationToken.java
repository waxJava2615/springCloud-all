package com.starry.sky.domain.service.authentication.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description 自定义过滤条件
 */
public class AdminLoginAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;
    private String verificationCode;

    public AdminLoginAuthenticationToken(Object principal, Object credentials,String verificationCode) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.verificationCode = verificationCode;
        this.setAuthenticated(false);
    }

    public AdminLoginAuthenticationToken(Object principal, Object credentials,String verificationCode,
                                         Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.verificationCode = verificationCode;
        super.setAuthenticated(true);
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }


    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * Checks the {@code credentials}, {@code principal} and {@code details} objects,
     * invoking the {@code eraseCredentials} method on any which implement
     * {@link CredentialsContainer}.
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
