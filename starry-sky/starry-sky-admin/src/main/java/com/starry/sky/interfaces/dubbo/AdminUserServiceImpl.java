package com.starry.sky.interfaces.dubbo;

import com.starry.sky.application.dto.AdminUserDTO;
import com.starry.sky.application.service.AdminUserAppService;
import com.starry.sky.infrastructure.constant.AdminConstant;
import com.starry.sky.infrastructure.dto.SysAdminUserDTO;
import com.starry.sky.interfaces.vo.UserDetailDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wax
 * @description: 后台用户信息
 * @date 2021-11-10
 */

@DubboService(version = "1.0.0",group = "${spring.application.name}",interfaceClass = IUserAdminService.class,timeout =
        50000)
public class AdminUserServiceImpl implements IUserAdminService {

    @Autowired
    AdminUserAppService userAdminAppService;


    /**
     * 根据用户ID 获取用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public UserDetailDTO loadByUserName(String userName) {
        SysAdminUserDTO sysAdminUserDTO = new SysAdminUserDTO();
        sysAdminUserDTO.setUserName(userName);
        AdminUserDTO adminUserDTO = userAdminAppService.loadByUserName(sysAdminUserDTO);
        if (adminUserDTO == null){
            return null;
        }
        return new UserDetailDTO(adminUserDTO.getId(),adminUserDTO.getLogo()
                ,adminUserDTO.getUserName(),adminUserDTO.getPassword(),adminUserDTO.getAuthorities(),
                adminUserDTO.getStatus() != AdminConstant.ACCOUNT_REMOVE,
                adminUserDTO.getStatus() != AdminConstant.ACCOUNT_LOCK,
                true,adminUserDTO.getStatus() == AdminConstant.ACCOUNT_DEFAULT);
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public UserDetailDTO loadByPhone(String phone) {
        return null;
    }
}
