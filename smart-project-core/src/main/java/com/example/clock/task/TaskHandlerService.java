package com.example.clock.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskHandlerService implements TaskHandler{
    @Override
    public void handler() {
        log.info("---------- TaskHandlerService handler -------");
    }
}
