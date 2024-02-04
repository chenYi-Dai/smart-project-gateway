package com.example.clock.dao.mapper;

import com.example.clock.dao.model.CustomerInfo;
import com.example.clock.form.CustomerInfoFrom;

import java.util.List;

public interface CustomerMapper {

    List<CustomerInfo> queryList(CustomerInfoFrom customerInfoFrom);
    int add(CustomerInfo customerInfo);
    void update(CustomerInfo customerInfo);
}
