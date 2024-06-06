package com.example.clock.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 测试springboot自动注入 注册组件
 */
@Slf4j
@Component
public class TestAutoResource {

/*    @PostConstruct
    public void init(){
        //该方法只要有Compoent 或者是在启动类上加上 注解就一定会执行
       log.info("----------- TestAutoResource init start -----------");
    }*/

    public void exportLogStr(String str){
        log.info("------exportLogStr str ------- | {}",str);
    }
}
