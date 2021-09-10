package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminUserDO;
import com.starry.sky.infrastructure.orm.po.SysAdminUser;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/4
 * @description 数据转换
 */
@Mapper(componentModel="spring")
public interface SysAdminUseAssembler {



    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "userName",source = "userName"),
            @Mapping(target = "phone",source = "phone"),
            @Mapping(target = "email",source = "email"),
            @Mapping(target = "password",source = "password"),
            @Mapping(target = "passwordSalt",source = "passwordSalt"),
            @Mapping(target = "status",source = "status"),
    })
    public SysAdminUserDO poToDO(SysAdminUser sysAdminUser);


    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminUser doToPO(SysAdminUserDO sysAdminUserDO);




}
