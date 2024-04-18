package com.example.clock.dao.mapper;

import com.example.clock.dao.model.CustomerInfo;
import com.example.clock.form.CustomerInfoFrom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerMapper {

    List<CustomerInfo> queryList(@Param("customerInfoFrom") CustomerInfoFrom customerInfoFrom);
    int add(CustomerInfo customerInfo);
    void update(CustomerInfo customerInfo);

/*    List<CustomerInfo> queryList(CustomerInfoFrom customerInfoFrom);*/
}
