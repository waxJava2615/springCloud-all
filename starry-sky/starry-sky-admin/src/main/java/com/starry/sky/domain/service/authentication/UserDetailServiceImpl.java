package com.starry.sky.domain.service.authentication;

import com.google.common.collect.Sets;
import com.starry.sky.common.constant.StarrySkyAdminConstants;
import com.starry.sky.common.utils.ResultCode;
import com.starry.sky.domain.entity.AuthenticationUser;
import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.domain.repository.SysAdminRoleDORepository;
import com.starry.sky.domain.repository.SysAdminUserDORepository;
import com.starry.sky.domain.repository.SysAdminUserRoleRelationDORepository;
import com.starry.sky.domain.service.authorization.casetwo.CustomGrantedAuthority;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import com.starry.sky.infrastructure.param.SysAdminRoleParam;
import com.starry.sky.infrastructure.param.SysAdminUserParam;
import com.starry.sky.infrastructure.param.SysAdminUserRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/8/22
 * @description userdetail实现类
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysAdminUserDORepository sysAdminUserDORepository;


    @Autowired
    private SysAdminRoleDORepository sysAdminRoleRepository;
    
    
    @Autowired
    private SysAdminUserRoleRelationDORepository sysAdminUserRoleRelationDORepository;


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
        SysAdminUserParam sysAdminUserParam = new SysAdminUserParam();
        sysAdminUserParam.setUserName(username);
        SysAdminUserDO sysAdminUserDO = sysAdminUserDORepository.findByUserName(sysAdminUserParam);
        if (sysAdminUserDO == null) {
            throw new UsernameNotFoundException(ResultCode.AUTHENTICATION_NOT_USER.getMessage());
        }
        // 根据用户获取用户权限
        List<SysAdminRoleDO> listRole = null;
        SysAdminUserRoleParam sysAdminUserRoleParam = SysAdminUserRoleParam.builder()
                .userId(sysAdminUserDO.getId())
                .build();
        List<SysAdminUserRoleRelationDO> listUserRoleRelation =
                sysAdminUserRoleRelationDORepository.findByUserId(sysAdminUserRoleParam);
        if (listUserRoleRelation != null){
            List<Long> listRoleId =
                    listUserRoleRelation.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());
            if (!listRoleId.isEmpty()){
                SysAdminRoleParam sysAdminRoleParam = SysAdminRoleParam.builder()
                        .listRoleId(listRoleId)
                        .build();
                listRole = sysAdminRoleRepository.findByIds(sysAdminRoleParam);
            }
        }
        if (listRole == null) {
            listRole = new ArrayList<>();
        }
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        for (SysAdminRoleDO sysAdminRoleDO : listRole) {
            authorities.add(new CustomGrantedAuthority(sysAdminRoleDO.getName()));
        }
        return new AuthenticationUser(sysAdminUserDO.getUserName(), sysAdminUserDO.getPassword(), authorities,
                sysAdminUserDO.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_EXPIRED,
                sysAdminUserDO.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_LOCK, true,
                sysAdminUserDO.getStatus() != StarrySkyAdminConstants.ACCOUNT_STATUS_ENABLED);

    }
}
