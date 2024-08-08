package com.example.clock.strategy;

import com.example.clock.strategy.domian.MessageInfoVo;
import org.springframework.stereotype.Component;

@Component
public class CddJobHandle extends HandleJob {
    @Override
    Object execJobHandle(MessageInfoVo o) {
        this.updateSql(o);
        return o;
    }

    @Override
    String getCurrentName() {
        return CddJobHandle.class.getSimpleName();
    }

    @Override
    public void updateSql(MessageInfoVo messageInfoVo){
        super.updateSql(messageInfoVo);
    }
}
