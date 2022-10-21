package com.starry.sky.interfaces.assembler;

import com.starry.sky.application.dto.AdminMenuDTO;
import com.starry.sky.infrastructure.utils.annotations.MappingCommonIgnore;
import com.starry.sky.interfaces.vo.AdminMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author wax
 * @description: vo转化
 * @date 2022-02-22
 */
@Mapper(componentModel="spring")
public interface AdminMenuConvert {

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "onlyKey",ignore = true),
    })
    @MappingCommonIgnore
    public AdminMenuVO dtoToVoSimple(AdminMenuDTO adminMenuDTO);

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "name",source = "name"),
            @Mapping(target = "onlyKey",ignore = true),
    })
    @MappingCommonIgnore
    public List<AdminMenuVO> dtoToVoSimple(List<AdminMenuDTO> list);
}
