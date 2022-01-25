package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminUserRoleRelationDO;
import com.starry.sky.infrastructure.orm.po.SysAdminUserRoleRelation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/4
 * @description 数据转换
 */
@Mapper(componentModel = "spring")
public interface SysAdminUserRoleRelationAssembler {


    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "ip", source = "ip"),
            @Mapping(target = "hide", source = "hide"),
            @Mapping(target = "order", source = "order"),
            @Mapping(target = "createTime", source = "createTime"),
            @Mapping(target = "updateTime", source = "updateTime"),
            @Mapping(target = "updatedBy",source = "updatedBy"),
            @Mapping(target = "userId" , source = "userId"),
            @Mapping(target = "roleId", source = "roleId"),})
    public SysAdminUserRoleRelationDO poToDO(SysAdminUserRoleRelation sysAdminUserRoleRelation);


    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminUserRoleRelation doToPO(SysAdminUserRoleRelationDO sysAdminUserRoleRelationDO);


    List<SysAdminUserRoleRelationDO> poToDoList(List<SysAdminUserRoleRelation> list);

}
