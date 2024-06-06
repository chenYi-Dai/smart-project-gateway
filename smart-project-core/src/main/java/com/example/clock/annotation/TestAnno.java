package com.example.clock.annotation;

import com.example.clock.config.TestAutoResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class TestAnno {

    @Resource
    private TestAutoResource testAnnotation;

    @PostConstruct
    public void init(){
        this.test();
    }

    public void test(){
        testAnnotation.exportLogStr("sgh");
    }
}
