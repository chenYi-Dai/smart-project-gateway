package com.example.clock.dao.mapper;

import com.example.clock.dao.model.HistAudit;

import java.util.List;

public interface HistAuditMapper {

    List<HistAudit> queryList(HistAudit histAudit);
    int add(HistAudit histAudit);
}
