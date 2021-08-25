package com.starry.sky.domain.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starry.sky.domain.repository.CommonRepository;

/**
 * @author wax
 * @description: TODO
 * @date 2021-08-23
 */
public class CommonRepositoryImpl <M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements CommonRepository<T> {



}
