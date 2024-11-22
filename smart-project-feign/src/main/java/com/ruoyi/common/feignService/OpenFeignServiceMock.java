package com.ruoyi.common.feignService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@ConditionalOnProperty(prefix = "cebs.feign.middle.aml.stub", name = "mode", havingValue = "mock")
@FeignClient(name = "${cebs.feign.middle.aml.stub.name:web-vb-base-grpc-gateway-mock}")
public interface OpenFeignServiceMock extends OpenFeignService{
}
