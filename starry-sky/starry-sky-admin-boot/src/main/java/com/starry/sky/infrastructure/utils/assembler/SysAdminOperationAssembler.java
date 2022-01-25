package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminOperationDO;
import com.starry.sky.infrastructure.orm.po.SysAdminOperation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @description: O转换
 * @date 2021-09-13
 */
@Mapper(componentModel="spring")
public interface SysAdminOperationAssembler {

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "operationCode",source = "operationCode"),
            @Mapping(target = "interceptUrlPrefix",source = "interceptUrlPrefix"),
            @Mapping(target = "parentId",source = "parentId"),
    })
    public SysAdminOperationDO poToDO(SysAdminOperation sysAdminOperation);

    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminOperation doToPO(SysAdminOperationDO sysAdminOperationDO);


    public List<SysAdminOperationDO> poToDOList(List<SysAdminOperation> list);

}
