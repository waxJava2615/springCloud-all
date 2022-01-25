package com.starry.sky.interfaces.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wax
 * @description: TODO
 * @date 2021-11-16
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseVO implements Serializable {

    private Long id;
}
