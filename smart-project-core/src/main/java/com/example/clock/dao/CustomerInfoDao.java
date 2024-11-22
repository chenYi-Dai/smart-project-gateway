package com.example.clock.dao;


import com.example.clock.dao.mapper.CustomerMapper;
import com.example.clock.dao.model.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public CustomerInfo query(CustomerInfo customerInfo) {
        log.info("query customerInfo |{}", customerInfo);
        return customerMapper.query(customerInfo);
    }
}
