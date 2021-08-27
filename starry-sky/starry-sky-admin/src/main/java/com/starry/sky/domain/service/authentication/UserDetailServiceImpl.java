package com.starry.sky.domain.service.authentication;

import com.google.common.collect.Sets;
import com.starry.sky.common.constant.StarrySkyAdminConstants;
import com.starry.sky.domain.entity.AuthenticationUser;
import com.starry.sky.domain.repository.SysAdminRoleRepository;
import com.starry.sky.domain.repository.SysAdminUserRepository;
import com.starry.sky.domain.service.authorization.casetwo.CustomGrantedAuthority;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @Autowired
    private SysAdminRoleRepository sysAdminRoleRepository;


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
        // 根据用户获取用户权限
        List<SysAdminRole> listRole = sysAdminRoleRepository.findByUserId(sysAdminUser.getId());
        if (listRole == null) {
            listRole = new ArrayList<>();
        }
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        for (SysAdminRole sysAdminRole : listRole) {
            authorities.add(new CustomGrantedAuthority(sysAdminRole.getName()));
        }
        return new AuthenticationUser(sysAdminUser.getUserName(), sysAdminUser.getPassword(), authorities,
                sysAdminUser.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_EXPIRED,
                sysAdminUser.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_LOCK, true,
                sysAdminUser.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_ENABLED);

    }
}
