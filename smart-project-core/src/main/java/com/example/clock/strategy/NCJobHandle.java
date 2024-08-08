package com.example.clock.strategy;

import com.example.clock.strategy.domian.MessageInfoVo;
import org.springframework.stereotype.Component;

@Component
public class NCJobHandle extends HandleJob {
    @Override
    Object execJobHandle(MessageInfoVo o) {
        this.updateSql(o);
        return o;
    }

    @Override
    String getCurrentName() {
        return NCJobHandle.class.getSimpleName();
    }

    @Override
    public void updateSql(MessageInfoVo messageInfoVo){
        super.updateSql(messageInfoVo);
    }
}
