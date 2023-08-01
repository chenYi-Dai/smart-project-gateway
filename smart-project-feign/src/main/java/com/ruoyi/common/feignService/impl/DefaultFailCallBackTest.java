package com.ruoyi.common.feignService.impl;

import com.ruoyi.common.entry.ResponseEntity;
import com.ruoyi.common.feignService.OpenFeignService;
import com.ruoyi.common.prarm.NodeListForm;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultFailCallBackTest implements FallbackFactory<OpenFeignService> {
    @Override
    public OpenFeignService create(Throwable throwable) {
        return new OpenFeignService(){

            @Override
            public ResponseEntity queryOneData(NodeListForm nodeListForm) {
                return new ResponseEntity("queryOneData DefaultFailCallBack 降级处理");
            }

            @Override
            public ResponseEntity queryAllData(NodeListForm nodeListForm) throws Exception {
                return new ResponseEntity("queryAllData DefaultFailCallBack 降级处理");
            }
        };
    }
}
