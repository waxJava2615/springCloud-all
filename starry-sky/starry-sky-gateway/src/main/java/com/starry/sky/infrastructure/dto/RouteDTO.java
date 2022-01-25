package com.starry.sky.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-08
 */
@Getter
@Setter
@NoArgsConstructor
public class RouteDTO {

    private List<RouteDefinition> routes;
}
