package com.example.clock.strategy;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StrategyFactory {

    private  static  final Map<String,HandleJob> HANDLE_JOB_MAP = new ConcurrentHashMap<>();

    public static void registerBean(String beanName,HandleJob handleJob){
        HANDLE_JOB_MAP.put(beanName,handleJob);
    }
    public static HandleJob getHandleInstance(String handleJobName){
        if(CollectionUtils.isEmpty(HANDLE_JOB_MAP)){
            return null;
        }
        if(StringUtils.isEmpty(handleJobName)){
            return null;
        }
        HandleJob handleJob = HANDLE_JOB_MAP.get(handleJobName);
        if(!ObjectUtils.isEmpty(handleJob)){
            return handleJob;
        }
        return null;
    }

}
