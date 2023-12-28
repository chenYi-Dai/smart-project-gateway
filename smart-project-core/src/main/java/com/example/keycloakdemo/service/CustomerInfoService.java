package com.example.keycloakdemo.service;

import com.example.keycloakdemo.dao.CustomerInfoDao;
import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.form.CustomerInfoFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CustomerInfoService {

    @Resource
    private CustomerInfoDao customerInfoDao;

    public List<CustomerInfo> getCustomerList(CustomerInfoFrom customerInfoFrom){
        log.info("customerInfoFrom |{}",customerInfoFrom);
        return customerInfoDao.getList(customerInfoFrom);
    }

    public void addCustomerInfo(CustomerInfo from){
        log.info("addCustomerInfo form |{}",from);
        customerInfoDao.add(from);
    }
}
