package com.starry.sky.infrastructure.oauth.userdetails.vip;

import com.starry.sky.infrastructure.utils.OauthConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wax
 * @description: 门户网站会员用户
 * @date 2021-10-11
 */
@Service
public class VipUserDetailService implements UserDetailsService {

    //注入dubbo 门户用户查询接口

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> list = new HashSet<>();
        list.add(new SimpleGrantedAuthority("user"));
        return new VipUserDetails(1L, OauthConstants.ACCOUNT_TYPE_PASSWORD, "waxVip","123456",list,false,false,true,false);
    }
}
