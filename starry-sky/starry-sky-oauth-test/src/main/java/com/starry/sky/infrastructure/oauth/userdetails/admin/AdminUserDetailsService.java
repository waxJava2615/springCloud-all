//package com.starry.sky.infrastructure.oauth.userdetails.admin;
//
//import com.starry.sky.interfaces.facade.AdminUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * @author wax
// * @description: TODO
// * @date 2021-10-09
// */
//@Slf4j
//@Service
//public class AdminUserDetailsService implements UserDetailsService {
//
//
//    @DubboReference(version = "2.0.1")
//    private AdminUserService adminUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        AdminUserDetails userDetails = null;
//        // 调用远程服务
//
//
//
//        if (userDetails == null) {
//            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMessage());
//        } else if (!userDetails.isEnabled()) {
//            throw new DisabledException("该账户已被禁用!");
//        } else if (!userDetails.isAccountNonLocked()) {
//            throw new LockedException("该账号已被锁定!");
//        } else if (!userDetails.isAccountNonExpired()) {
//            throw new AccountExpiredException("该账号已过期!");
//        }
//        return userDetails;
//    }
//
//}
