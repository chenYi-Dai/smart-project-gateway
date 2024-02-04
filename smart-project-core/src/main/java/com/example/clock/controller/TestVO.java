package com.example.clock.controller;

import com.example.clock.dao.model.CustomerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestVO {
    private Integer page;
    private Integer pageSize;

    private Integer pageNum;

    private Long total;

    private List<CustomerInfo> list;
}
