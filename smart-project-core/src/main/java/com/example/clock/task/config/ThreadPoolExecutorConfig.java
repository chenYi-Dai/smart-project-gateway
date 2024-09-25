package com.example.clock.task.config;

import com.dtp.common.em.QueueTypeEnum;
import com.dtp.core.thread.DtpExecutor;
import com.dtp.core.thread.ThreadPoolBuilder;
import com.example.clock.constant.ThreadPoolConstant;

import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorConfig {

    public static DtpExecutor getDtpExecutor(){
        return ThreadPoolBuilder.newBuilder()
                .threadPoolName("dynamic-thread-pool")
                .corePoolSize(ThreadPoolConstant.COMMON_CORE_POOL_SIZE)
                .maximumPoolSize(ThreadPoolConstant.COMMON_MAX_POOL_SIZE)
                .keepAliveTime(ThreadPoolConstant.COMMON_KEEP_LIVE_TIME)
                .timeUnit(TimeUnit.SECONDS)
                .rejectedExecutionHandler("CallerRunsPolicy")
                .allowCoreThreadTimeOut(false)
                .workQueue(QueueTypeEnum.VARIABLE_LINKED_BLOCKING_QUEUE.getName(),ThreadPoolConstant.COMMON_QUEUE_SIZE, false)
                .buildDynamic();
    }
}
