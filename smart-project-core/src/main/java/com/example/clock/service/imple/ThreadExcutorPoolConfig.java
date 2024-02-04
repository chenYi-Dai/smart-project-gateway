package com.example.clock.service.imple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : ex_yi.chen
 * @ClassName : ThreadExcutorPool
 * @Description :
 * @Date: 2023-02-06 17:28
 */
@Slf4j
@Component
@Configuration
public class ThreadExcutorPoolConfig {

    @Value("${async.executor.thread.max_pool_size}")
    private int maxPoolSize = 3;

    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize = 2;

    @Value("${async.executor.thread.queue_capacity}")
    private int queueCapacity = 5;

    @Value("${async.executor.thread.name.prefix}")
    private String namePrefix ="importDate";

    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();

        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);

        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);

        //配置队列大小
        executor.setQueueCapacity(queueCapacity);

        executor.setKeepAliveSeconds(1);

        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
