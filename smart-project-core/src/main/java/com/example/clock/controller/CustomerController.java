package com.example.clock.controller;


import com.example.clock.adapter.CustomerUserServiceImpl;
import com.example.clock.annotation.TestAnnotation;
import com.example.clock.dao.model.CustomerInfo;
import com.example.clock.service.CustomerInfoService;
import com.example.clock.strategy.TestServiceImpl;
import com.example.clock.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(value = "CustomerController", tags = {"客户信息"})
@RequestMapping(value = "/smart/customer")
public class CustomerController {

    @Resource
    CustomerInfoService customerInfoService;

    @TestAnnotation
    @GetMapping("getList")
    @ApiOperation(value = "获取列表信息",notes = "testCustomer")
    public ResponseEntity<List<CustomerInfo>> testCustomer(){
        customerInfoService.addCustomerInfo(CustomerInfo.builder().mobile("13798281964").build());
        return new ResponseEntity();
    }

    @PostMapping("/add")
    @ApiOperation( value = "添加客户信息",notes = "addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerInfo from){
        customerInfoService.addCustomerInfo(from);
        return new ResponseEntity<>();
    }

    @PostMapping("/update")
    @ApiOperation( value = "添加客户信息",notes = "updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerInfo from){
        customerInfoService.updateCustomerInfo(from);
        return new ResponseEntity<>();
    }

    @Resource
    private TestServiceImpl testService;

    @Resource
    private CustomerUserServiceImpl service;

    @GetMapping("/testFactory")
    @ApiOperation( value = "添加客户信息",notes = "updateCustomer")
    public void testFactory(){
        service.queryCustomerInfo();
        testService.testFactory();
    }

}
