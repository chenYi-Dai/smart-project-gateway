package com.example.clock.redact;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 基于注解 进行切面拦截
 */
@Slf4j
@Aspect
@Component
public class AnnoAspect {

    @Pointcut("@annotation(com.example.clock.annotation.TestAnnotation)")
    public void optLogPointCut(){

    }

    @Around("optLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("AnnoAspect around | {}", JSONObject.toJSONString(args));
        return args;
    }

}
