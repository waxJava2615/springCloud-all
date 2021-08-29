package com.starry.sky.domain.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starry.sky.infrastructure.orm.po.BaseEntity;

/**
 * @author wax
 * @description: 抽象公共方法
 * @date 2021-08-23
 */
public interface BaseRepository<T extends BaseEntity> extends IService<T> {


}
