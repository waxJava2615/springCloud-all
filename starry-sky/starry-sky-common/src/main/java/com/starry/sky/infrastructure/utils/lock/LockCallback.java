package com.starry.sky.infrastructure.utils.lock;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-07
 */
public interface LockCallback<T> {

    /**
     * 业务实现
     *
     * @return
     */
    T doBusiness();

}
