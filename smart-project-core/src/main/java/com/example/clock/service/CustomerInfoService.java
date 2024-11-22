package com.example.clock.service;

import com.example.clock.config.SmartProjectBusinessException;
import com.example.clock.dao.CustomerInfoDao;
import com.example.clock.dao.model.CustomerInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CustomerInfoService {

    @Resource
    private CustomerInfoDao customerInfoDao;


    public void addCustomerInfo(CustomerInfo from){
        log.info("addCustomerInfo form |{}",from);
        customerInfoDao.add(from);
    }

    public void updateCustomerInfo(CustomerInfo from){
        log.info("updateCustomerInfo form |{}",from);
        customerInfoDao.update(from);
    }

    public void batchInsertCustomer(List<CustomerInfo> from) {
        for(CustomerInfo customerInfo : from){
            CustomerInfo query = customerInfoDao.query(customerInfo);
            if(!ObjectUtils.isEmpty(query)){
                throw new SmartProjectBusinessException("数据插入失败");
            }
            customerInfoDao.add(customerInfo);
        }
    }
}
