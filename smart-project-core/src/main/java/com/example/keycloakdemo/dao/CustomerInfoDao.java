package com.example.keycloakdemo.dao;


import com.example.keycloakdemo.dao.mapper.CustomerMapper;
import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.form.CustomerInfoFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class CustomerInfoDao {

    @Resource
    CustomerMapper customerMapper;

    public List<CustomerInfo> getList(CustomerInfoFrom form){
        log.info("getList customerInfoFrom |{}",form);
        List<CustomerInfo> customerInfos = customerMapper.queryList(form);
        return customerInfos;
    }

    public void add(CustomerInfo customerInfo) {
        log.info("add customerInfo |{}",customerInfo);
        customerMapper.add(customerInfo);
    }
}
