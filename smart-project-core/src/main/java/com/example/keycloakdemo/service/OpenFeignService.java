package com.example.keycloakdemo.service;

import com.example.keycloakdemo.form.NodeListForm;
import com.example.keycloakdemo.vo.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "smart-project-gateway")
public interface OpenFeignService {

    @ApiOperation(value = "查询节点数据")
    @RequestMapping(value = "/query/node",method = RequestMethod.POST)
    public ResponseEntity queryOneData(@RequestBody NodeListForm nodeListForm);
}
