package com.starry.sky.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wax
 * @version 1.0.0
 * @date 2021/9/5
 * @description 基础类
 */

@Getter
@Setter
@NoArgsConstructor
public class BaseDomainDTO implements Serializable {
    
    
    private Integer pageNo;
    
    
    private Integer pageSize;
    
    
    
}
