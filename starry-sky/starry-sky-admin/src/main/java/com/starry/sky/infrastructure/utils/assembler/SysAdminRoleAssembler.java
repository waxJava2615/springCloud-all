package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminRoleDO;
import com.starry.sky.infrastructure.orm.po.SysAdminRole;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @description: 角色PO-DO-DTO
 * @date 2021-09-07
 */
@Mapper(componentModel="spring")
public interface SysAdminRoleAssembler {


    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "name",source = "name"),
    })
    SysAdminRoleDO poToDO(SysAdminRole sysAdminRole);

    @InheritInverseConfiguration(name = "poToDO")
    SysAdminRole doToPO(SysAdminRoleDO sysAdminRoleDO);


    List<SysAdminRoleDO> poToDOList(List<SysAdminRole> sysAdminRoleList);

}
