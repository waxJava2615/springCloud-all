package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminRolePermissionRelationDO;
import com.starry.sky.infrastructure.orm.po.SysAdminRolePermissionRelation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 角色权限O转换
 */

@Mapper(componentModel="spring")
public interface SysAdminRolePermissionRelationAssembler {
    
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "roleId",source = "roleId"),
            @Mapping(target = "permissionId",source = "permissionId"),
    })
    public SysAdminRolePermissionRelationDO poToDO(SysAdminRolePermissionRelation sysAdminRolePermissionRelation);
    
    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminRolePermissionRelation doToPO(SysAdminRolePermissionRelationDO sysAdminRolePermissionRelationDO);
    
    
    public List<SysAdminRolePermissionRelationDO> poToDOList(List<SysAdminRolePermissionRelation> list);
    
}
