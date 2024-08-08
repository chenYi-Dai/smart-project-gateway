package com.ruoyi.common.feignService;

import com.ruoyi.common.entry.ResponseEntity;
import com.ruoyi.common.prarm.NodeListForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@FeignClient(value = "smart-project-gateway")
@RestController
public interface OpenFeignService {

    @ApiOperation(value = "查询节点数据")
    @RequestMapping(value = "/query/node",method = RequestMethod.POST)
    public ResponseEntity queryOneData(@RequestBody NodeListForm nodeListForm) throws Exception;

    @ApiOperation(value = "查询节点数据")
    @RequestMapping(value = "/query/list",method = RequestMethod.POST)
    public ResponseEntity queryAllData(@RequestBody NodeListForm nodeListForm) throws Exception;
}
