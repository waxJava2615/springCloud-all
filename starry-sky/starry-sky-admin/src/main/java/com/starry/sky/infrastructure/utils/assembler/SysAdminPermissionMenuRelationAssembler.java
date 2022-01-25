package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminPermissionMenuRelationDO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionMenuRelation;
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
public interface SysAdminPermissionMenuRelationAssembler {
    
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "updatedBy",source = "updatedBy"),
            @Mapping(target = "permissionId",source = "permissionId"),
            @Mapping(target = "menuId",source = "menuId"),
    })
    public SysAdminPermissionMenuRelationDO poToDO(SysAdminPermissionMenuRelation sysAdminPermissionMenuRelation);
    
    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminPermissionMenuRelation doToPO(SysAdminPermissionMenuRelationDO sysAdminPermissionMenuRelationDO);
    
    
    public List<SysAdminPermissionMenuRelationDO> poToDOList(List<SysAdminPermissionMenuRelation> list);
    
}
