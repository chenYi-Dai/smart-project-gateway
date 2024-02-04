package com.example.clock.service;

import com.example.clock.dao.CustomerInfoDao;
import com.example.clock.dao.model.CustomerInfo;
import com.example.clock.form.CustomerInfoFrom;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<CustomerInfo> pageList(CustomerInfoFrom customerInfoFrom){
        log.info("customerInfoFrom |{}",customerInfoFrom);
        PageHelper.startPage(customerInfoFrom.getStart(), customerInfoFrom.getSize());
        List<CustomerInfo> list = customerInfoDao.getList(customerInfoFrom);
        PageInfo<CustomerInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void addCustomerInfo(CustomerInfo from){
        log.info("addCustomerInfo form |{}",from);
        customerInfoDao.add(from);
    }

    public void updateCustomerInfo(CustomerInfo from){
        log.info("updateCustomerInfo form |{}",from);
        customerInfoDao.update(from);
    }
}
