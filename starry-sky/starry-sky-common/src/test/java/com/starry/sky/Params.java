package com.starry.sky;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-09
 */
@Getter
@Setter
@NoArgsConstructor
public class Params implements Serializable {

    private Integer pageNum;


    private Integer pageSize;


}
