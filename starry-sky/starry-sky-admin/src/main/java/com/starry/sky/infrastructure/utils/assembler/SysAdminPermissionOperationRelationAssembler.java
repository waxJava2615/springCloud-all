package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminPermissionOperationRelationDO;
import com.starry.sky.infrastructure.orm.po.SysAdminPermissionOperationRelation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/11
 * @description 权限操作O转换
 */

@Mapper(componentModel="spring")
public interface SysAdminPermissionOperationRelationAssembler {
    
    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "updatedBy",source = "updatedBy"),
            @Mapping(target = "permissionId",source = "permissionId"),
            @Mapping(target = "operationId",source = "operationId"),
    })
    public SysAdminPermissionOperationRelationDO poToDO(SysAdminPermissionOperationRelation sysAdminPermissionOperationRelation);
    
    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminPermissionOperationRelation doToPO(SysAdminPermissionOperationRelationDO sysAdminPermissionOperationRelationDO);
    
    
    public List<SysAdminPermissionOperationRelationDO> poToDOList(List<SysAdminPermissionOperationRelation> list);
}
