//package com.starry.sky.interfaces.facade;
//
//import com.starry.sky.interfaces.dto.AdminUserDto;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.springframework.stereotype.Component;
//
///**
// * @author wax
// * @description: 后台用户服务提供者
// * @date 2021-10-09
// */
//@DubboService(version = "1.0.0", interfaceClass = AdminUserService.class,
//        group = "${spring.application.name}", timeout = 3000)
//@Component
//public class AdminUserServiceImpl implements AdminUserService {
//
//    @Override
//    public AdminUserDto loadUserById(Long id) {
//        AdminUserDto adminUserDto = new AdminUserDto();
//        adminUserDto.setId(1L);
//        adminUserDto.setUserName("wax");
//        adminUserDto.setStatus(1);
//        adminUserDto.setPassword("123456");
//        return adminUserDto;
//    }
//
//    @Override
//    public AdminUserDto loadUserByUserName(String userName) {
//        AdminUserDto adminUserDto = new AdminUserDto();
//        adminUserDto.setId(1L);
//        adminUserDto.setUserName("wax");
//        adminUserDto.setStatus(1);
//        adminUserDto.setPassword("123456");
//        return adminUserDto;
//    }
//
//
//}
