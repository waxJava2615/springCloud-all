package com.starry.sky.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wax
 * @description: 线程池
 * @date 2021-09-06
 */
public class ThreadPool {


    public void init(){
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
    }


}
