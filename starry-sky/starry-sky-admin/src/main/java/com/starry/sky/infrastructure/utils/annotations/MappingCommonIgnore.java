package com.starry.sky.infrastructure.utils.annotations;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author wax
 * @description: TODO
 * @date 2022-02-22
 */

@Retention(RetentionPolicy.CLASS)
@Mappings(value = {
        @Mapping(target = "ip", ignore = true),
        @Mapping(target = "hide", ignore = true),
        @Mapping(target = "order", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
})
public @interface MappingCommonIgnore {
}
