package com.ruoyi.common.feignService;

import com.ruoyi.common.entry.ResponseEntity;
import com.ruoyi.common.prarm.NodeListForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Component
@FeignClient(value = "smart-project-gateway",primary = false)
@RestController
public interface OpenFeignService {

    @ApiOperation(value = "查询节点数据")
    @RequestMapping(value = "/query/node",method = RequestMethod.POST)
    public ResponseEntity queryOneData(@RequestBody NodeListForm nodeListForm) throws Exception;

    @ApiOperation(value = "查询节点数据")
    @RequestMapping(value = "/query/list",method = RequestMethod.POST)
    public ResponseEntity queryAllData(@RequestBody NodeListForm nodeListForm) throws Exception;

    @PostConstruct
    public default void test11() {
        if (this.getClass().getAnnotation(ConditionalOnProperty.class).havingValue().equals("true")) {
            System.out.println("配置类生效");
        } else {
            System.out.println("配置类未生效");
        }
    }
}
