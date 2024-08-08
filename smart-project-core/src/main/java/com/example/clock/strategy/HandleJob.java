package com.example.clock.strategy;

import com.alibaba.fastjson.JSONObject;
import com.example.clock.strategy.domian.MessageInfoVo;

import javax.annotation.PostConstruct;

public abstract class HandleJob {



    abstract Object execJobHandle(MessageInfoVo t);

    abstract String getCurrentName();

    @PostConstruct
    protected void init() {
        StrategyFactory.registerBean(getCurrentName(), this);
    }

    protected void updateSql(MessageInfoVo messageInfoVo){
        System.out.println("messageInfoVo :"+ JSONObject.toJSONString(messageInfoVo));
    }
}
