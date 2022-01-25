package com.starry.sky.application.assembler;

import cn.hutool.core.bean.BeanUtil;
import com.starry.sky.application.dto.AdminOperationDTO;
import com.starry.sky.domain.entity.SysAdminOperationDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wax
 * @description: 将操作DO 转换陈dto
 * @date 2021-12-20
 */
@Service
public class AdminOperationAppAssembler {


    /**
     * 根据模块
     * @param listOperationDO
     * @return
     */
    public List<AdminOperationDTO> convertDTO(List<SysAdminOperationDO> listOperationDO){


        return  null;
    }

    public AdminOperationDTO convertDTO(SysAdminOperationDO sysAdminOperationDO,boolean allField){
        if(sysAdminOperationDO == null){
            return null;
        }
        AdminOperationDTO operationDTO = new AdminOperationDTO();
        if(allField){
            operationDTO = BeanUtil.copyProperties(sysAdminOperationDO, AdminOperationDTO.class);
        }else {
            operationDTO.setName(sysAdminOperationDO.getName());
            operationDTO.setIcon(sysAdminOperationDO.getIcon());
        }
        return  operationDTO;
    }


}
