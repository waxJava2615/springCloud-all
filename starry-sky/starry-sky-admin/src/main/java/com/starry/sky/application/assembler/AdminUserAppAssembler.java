package com.starry.sky.application.assembler;

import com.starry.sky.application.dto.AdminUserDTO;
import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.infrastructure.constant.CommonConstants;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-16
 */
@Service
public class AdminUserAppAssembler {


    public AdminUserDTO convertDTO(SysAdminUserDO sysAdminUserDO, int includePwd) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        adminUserDTO.setId(sysAdminUserDO.getId());
        adminUserDTO.setUserName(sysAdminUserDO.getUserName());
        adminUserDTO.setEmail(sysAdminUserDO.getEmail());
        if (includePwd == CommonConstants.YES){
            adminUserDTO.setPassword(sysAdminUserDO.getPassword());
        }
        adminUserDTO.setPhone(sysAdminUserDO.getPhone());
        adminUserDTO.setStatus(sysAdminUserDO.getStatus());
        return adminUserDTO;
    }
}
