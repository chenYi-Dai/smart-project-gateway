package com.example.keycloakdemo.controller;


import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.form.CustomerInfoFrom;
import com.example.keycloakdemo.service.CustomerInfoService;
import com.example.keycloakdemo.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(value = "CustomerController", tags = {"客户信息"})
public class CustomerController {

    @Resource
    CustomerInfoService customerInfoService;
    @GetMapping("getList")
    @ApiOperation(value = "获取列表信息",notes = "testCustomer")
    public ResponseEntity<List<CustomerInfo>> testCustomer(){
        List<CustomerInfo> customerList = customerInfoService.getCustomerList(CustomerInfoFrom.builder().mobile("13798281964").build());
        return new ResponseEntity(customerList);
    }

    @PostMapping("/add")
    @ApiOperation( value = "添加客户信息",notes = "testCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerInfo from){
        customerInfoService.addCustomerInfo(from);
        return new ResponseEntity<>();
    }


}
