package com.starry.sky.application.assembler;

import com.google.common.collect.Lists;
import com.starry.sky.application.dto.AdminUserRoleRelationDTO;
import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wax
 * @description: 数据转换
 * @date 2021-11-16
 */
@Service
public class AdminUserRoleRelationAppAssembler {


    public AdminUserRoleRelationDTO convertDTO(SysAdminUserRoleRelationDO sysAdminUserRoleRelationDO){

        if (sysAdminUserRoleRelationDO == null){
            return null;
        }
        AdminUserRoleRelationDTO adminUserRoleRelationDTO = new AdminUserRoleRelationDTO();
        adminUserRoleRelationDTO.setUserId(sysAdminUserRoleRelationDO.getUserId());
        adminUserRoleRelationDTO.setRoleId(sysAdminUserRoleRelationDO.getRoleId());
        return adminUserRoleRelationDTO;
    }

    public List<AdminUserRoleRelationDTO> convertDTO(List<SysAdminUserRoleRelationDO> list){
        if (CollectionUtils.isEmpty(list)){
            return Collections.emptyList();
        }
        List<AdminUserRoleRelationDTO> listDto = Lists.newArrayList();
        list.forEach(ur ->{
            AdminUserRoleRelationDTO adminUserRoleRelationDTO = convertDTO(ur);
            listDto.add(adminUserRoleRelationDTO);
        });
        return listDto;
    }

}
