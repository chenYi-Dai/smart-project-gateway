package com.example.clock.task;

import com.dtp.core.DtpRegistry;
import com.dtp.core.thread.DtpExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ThreadUtils {

    @Resource
    private ThreadPoolExecutorShutdownDefinition executorShutdownDefinition;

    public void registDynamicPool(DtpExecutor executor){
        DtpRegistry.register(executor,"smart");
        executorShutdownDefinition.registryExecutor(executor);
    }
}
