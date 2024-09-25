package com.example.clock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ThreadPoolExecutorShutdownDefinition implements ApplicationListener<ContextClosedEvent> {

    /**
     * 线程中的任务在接收到应用关闭信号量后最多等待多久就强制终止，其实就是给剩余任务预留的时间， 到时间后线程池必须销毁
     */
    private static final long AWAIT_TERMINATION = 20;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final List<ExecutorService> pools = Collections.synchronizedList(new ArrayList<>(10));

    public void registryExecutor(ExecutorService executor) {
        pools.add(executor);
    }
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

        System.out.println(11111);
        if (CollectionUtils.isEmpty(pools)) {
            return;
        }
        for(ExecutorService executorService : pools){
            log.info("ThreadPoolExecutorShutdownDefinition onApplicationEvent service");
            executorService.shutdown();
        }
    }
}
