package com.starry.sky.infrastructure.utils.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author wax
 * @description: 分布式锁
 * @date 2021-09-07
 */
@Slf4j
public class RedissonLockTemplate {

    private final long waitTime = 5;

    private final long leaseTime = 10;

    private final TimeUnit timeUnit = TimeUnit.SECONDS;

    private RedissonClient redissonClient;

    public RedissonLockTemplate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    public <T> T tryLock(String lockName,LockCallback<T> lockCallback){
        return tryLock(lockName,waitTime,leaseTime,timeUnit,lockCallback);
    }



    /**
     * 获取锁
     * @param lockName  所名称
     * @param waitTime  等待时长
     * @param leaseTime 锁时长
     * @param timeUnit 时间单位
     * @param lockCallback 业务逻辑
     * @return
     */
    public <T> T tryLock(String lockName, long waitTime, long leaseTime, TimeUnit timeUnit, LockCallback<T> lockCallback){
        RLock lock = redissonClient.getLock(lockName);
        T result = null;
        try {
            boolean tryLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (tryLock){
                result = lockCallback.doBusiness();
            }
        } catch (InterruptedException e) {
            log.error("{} 获取锁失败",lockName,e);
        }finally {
            lock.unlock();
        }
        return result;
    }


    public <T> T lock(String lockName,LockCallback<T> lockCallback){
        RLock lock = redissonClient.getLock(lockName);

        T result = null;
        try {
            lock.lock();
            result = lockCallback.doBusiness();
        } catch (Exception e) {
            log.error("{} 获取锁失败",lockName,e);
        }finally {
            lock.unlock();
        }
        return result;
    }

}
