package com.example.clock.dao;

import com.example.clock.dao.mapper.HistAuditMapper;
import com.example.clock.dao.model.HistAudit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class HistAuditDao {

    @Resource
    private HistAuditMapper histAuditMapper;
    public List<HistAudit> list(HistAudit histAudit){
        log.info("HistAuditDao list |{}",histAudit);
        List<HistAudit> list = histAuditMapper.queryList(histAudit);
        return list;
    }

    public void addHistAudit(HistAudit histAudit){
        log.info("HistAuditDao addHistAudit |{}",histAudit);
        histAuditMapper.add(histAudit);
    }
}
