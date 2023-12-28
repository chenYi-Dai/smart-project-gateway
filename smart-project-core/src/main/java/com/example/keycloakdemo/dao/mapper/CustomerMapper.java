package com.example.keycloakdemo.dao.mapper;

import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.form.CustomerInfoFrom;

import java.util.List;

public interface CustomerMapper {

    List<CustomerInfo> queryList(CustomerInfoFrom customerInfoFrom);
    int add(CustomerInfo customerInfo);
    void update(CustomerInfo customerInfo);
}
