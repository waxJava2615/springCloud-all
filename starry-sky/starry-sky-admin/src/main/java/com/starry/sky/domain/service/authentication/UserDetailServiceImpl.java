package com.starry.sky.domain.service.authentication;

import com.google.common.collect.Lists;
import com.starry.sky.domain.repository.SysAdminUserRepository;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description userdetail实现类
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysAdminUserRepository sysAdminUserRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysAdminUser sysAdminUser = (SysAdminUser) sysAdminUserRepository.findByUserName(username);
        if (sysAdminUser == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        List<GrantedAuthority> authorities = Lists.newArrayList();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return new User(sysAdminUser.getUserName(), sysAdminUser.getPassword(), true, true, true, true, authorities);

    }
}
