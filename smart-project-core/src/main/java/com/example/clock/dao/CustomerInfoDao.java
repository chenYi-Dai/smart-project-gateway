package com.example.clock.dao;


import com.example.clock.dao.mapper.CustomerMapper;
import com.example.clock.dao.model.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class CustomerInfoDao {

    @Resource
    CustomerMapper customerMapper;


    public void add(CustomerInfo customerInfo) {
        log.info("add customerInfo |{}", customerInfo);
        customerMapper.add(customerInfo);
    }

    public void update(CustomerInfo customerInfo) {
        log.info("CustomerInfoDao update customerInfo | {}",customerInfo);
        customerMapper.update(customerInfo);
    }
}
