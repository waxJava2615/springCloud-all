package com.starry.sky.infrastructure.events.sources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wax
 * @description: 事件数据基类
 * @date 2021-09-22
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseSourceDTO implements Serializable {

    private String currentTable;
}
