package com.starry.sky.infrastructure.orm.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.sky.infrastructure.orm.po.BaseEntity;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-23
 */
public class BaseRepositoryImpl<M extends BaseMapper<T>, T  extends BaseEntity> extends ServiceImpl<M, T>
        implements IService<T> {


}
