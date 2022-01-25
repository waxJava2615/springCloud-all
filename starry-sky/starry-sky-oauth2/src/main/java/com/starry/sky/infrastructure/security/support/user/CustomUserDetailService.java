package com.starry.sky.infrastructure.security.support.user;

import cn.hutool.core.bean.BeanUtil;
import com.starry.sky.infrastructure.constant.CommonConstants;
import com.starry.sky.infrastructure.dto.UserDTO;
import com.starry.sky.infrastructure.exceptions.CustomAuthenticationException;
import com.starry.sky.infrastructure.utils.ResUtils;
import com.starry.sky.infrastructure.utils.ThreadLocalHolder;
import com.starry.sky.infrastructure.utils.enums.OauthResultCode;
import com.starry.sky.interfaces.dubbo.IUserAdminService;
import com.starry.sky.interfaces.vo.UserDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wax
 * @description: 门户网站会员用户
 * @date 2021-10-11
 */
@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {


    private Map<String,CustomUserDetails> dtoMap = new HashMap<>();

    //注入dubbo 门户用户查询接口

    @DubboReference(version = "1.0.0",group = CommonConstants.DUBBO_SUB_STARRY_SKY_ADMIN ,interfaceClass =
            IUserAdminService.class)
    IUserAdminService iUserAdminService;



    @PostConstruct
    public void initDate(){
        Set<GrantedAuthority> adminAuthor = new HashSet<>();
        adminAuthor.add(new SimpleGrantedAuthority("admin"));
        UserDTO adminUserDetailDTO = new UserDTO(1L,null,"admin","admin",adminAuthor,true,true,true,true);
        dtoMap.put("admin",new CustomUserDetails(adminUserDetailDTO));

        Set<GrantedAuthority> vipAuthor = new HashSet<>();
        vipAuthor.add(new SimpleGrantedAuthority("vip"));
        UserDTO vipUserDetailDTO = new UserDTO(1L,null, "vip","vip",vipAuthor,true,true,true,true);
        dtoMap.put("vip",new CustomUserDetails(vipUserDetailDTO));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResUtils resUtils = ThreadLocalHolder.getResUtils().get();
        HttpServletRequest request = resUtils.getRequest();
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String clientId = resUtils.getParam("client_id");
        // 更具不同的clientId获取不同的service服务？
        UserDetailDTO userDetailDTO = iUserAdminService.loadByUserName(username);
        UserDTO userDTO = BeanUtil.toBean(userDetailDTO, UserDTO.class);
        if (userDTO == null) {
            throw new CustomAuthenticationException(OauthResultCode.AUTHENTICATION_NOT_USER);
        }
        List<String> listRole = userDetailDTO.getListRole();
        if (listRole == null){
            listRole = Collections.emptyList();
        }
        Set<GrantedAuthority> setRole = new HashSet<>();
        listRole.forEach(lr -> setRole.add(new SimpleGrantedAuthority(lr)));
        userDTO.setAuthorities(setRole);
        CustomUserDetails userDetails = new CustomUserDetails(userDTO);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }
}
