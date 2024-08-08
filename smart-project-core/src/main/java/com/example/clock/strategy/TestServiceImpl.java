package com.example.clock.strategy;

import com.example.clock.strategy.domian.MessageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl {

    @Resource
    private CddJobHandle cddJobHandle;
    public void testFactory(){
        HandleJob handleInstance = StrategyFactory.getHandleInstance(cddJobHandle.getCurrentName());
        MessageInfoVo messageInfoVo = new MessageInfoVo();
        assert handleInstance != null;
        Object o = handleInstance.execJobHandle(messageInfoVo);
        System.out.println(o);
    }
}
