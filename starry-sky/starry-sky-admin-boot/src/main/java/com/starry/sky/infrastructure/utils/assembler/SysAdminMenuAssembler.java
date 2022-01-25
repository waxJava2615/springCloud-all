package com.starry.sky.infrastructure.utils.assembler;

import com.starry.sky.domain.entity.SysAdminMenuDO;
import com.starry.sky.infrastructure.dto.SysAdminMenuDTO;
import com.starry.sky.infrastructure.orm.po.SysAdminMenu;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @description: 菜单O转换
 * @date 2021-09-13
 */
@Mapper(componentModel="spring")
public interface SysAdminMenuAssembler {


    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "url",source = "url"),
            @Mapping(target = "parentId",source = "parentId"),
    })
    public SysAdminMenuDO poToDO(SysAdminMenu sysAdminMenu);


    @InheritInverseConfiguration(name = "poToDO")
    public SysAdminMenu doToPO(SysAdminMenuDO sysAdminMenuDO);


    public List<SysAdminMenuDO> poToDOList(List<SysAdminMenu> list);


    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "url",source = "url"),
            @Mapping(target = "parentId",source = "parentId"),
    })
    public SysAdminMenuDTO doToDTO(SysAdminMenuDO sysAdminMenuDO);

    @InheritInverseConfiguration(name = "doToDTO")
    public SysAdminMenuDO dtoToDO(SysAdminMenuDTO sysAdminMenuDTO);


    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "ip",source = "ip"),
            @Mapping(target = "hide",source = "hide"),
            @Mapping(target = "order",source = "order"),
            @Mapping(target = "createTime",source = "createTime"),
            @Mapping(target = "updateTime",source = "updateTime"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "url",source = "url"),
            @Mapping(target = "parentId",source = "parentId"),
    })
    public SysAdminMenu dtoToPO(SysAdminMenuDTO sysAdminMenuDTO);

    @InheritInverseConfiguration(name = "dtoToPO")
    public SysAdminMenu poToDTO(SysAdminMenuDTO sysAdminMenuDTO);

}
