package com.starry.sky.infrastructure.oauth.extension;

import com.starry.sky.infrastructure.oauth.userdetails.admin.AdminUserDetailService;
import com.starry.sky.infrastructure.utils.AccountTypeEnum;
import com.starry.sky.infrastructure.utils.OauthConstants;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-11
 */
public class PreAuthenticatedUserDetailsService<T extends Authentication> implements AuthenticationUserDetailsService<T>, InitializingBean {

    private Map<String, UserDetailsService> userDetailsServiceMap;

    public PreAuthenticatedUserDetailsService(Map<String, UserDetailsService> userDetailsServiceMap) {
        Assert.notNull(userDetailsServiceMap, "userDetailsService cannot be null.");
        this.userDetailsServiceMap = userDetailsServiceMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsServiceMap, "UserDetailsService must be set");
    }

    @Override
    public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
        String clientId = ThreadLocalHolder.getResUtils().get().getOAuth2ClientId();
        String accountType = ThreadLocalHolder.getResUtils().get().getParam("accountType",
                OauthConstants.ACCOUNT_TYPE_PASSWORD);
        AccountTypeEnum accountTypeEnum = AccountTypeEnum.getByType(accountType);
        UserDetailsService userDetailsService = userDetailsServiceMap.get(clientId);
        if (clientId.equals(OauthConstants.ADMIN_CLIENT_ID)) {
            AdminUserDetailService memberUserDetailsService = (AdminUserDetailService) userDetailsService;
            switch (accountTypeEnum) {
                case MOBILE:
                    // return memberUserDetailsService.loadUserByOpenId(authentication.getName());

                default:
                    return memberUserDetailsService.loadUserByUsername(authentication.getName());
            }
        } else {
            return userDetailsService.loadUserByUsername(authentication.getName());
        }
    }


}
