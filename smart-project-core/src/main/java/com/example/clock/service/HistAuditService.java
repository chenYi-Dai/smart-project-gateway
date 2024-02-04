package com.example.clock.service;

import com.example.clock.dao.HistAuditDao;
import com.example.clock.dao.mapper.HistAuditMapper;
import com.example.clock.dao.model.HistAudit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class HistAuditService {

    @Resource
    private HistAuditDao histAuditDao;
    public List<HistAudit> getList(HistAudit histAudit){
        log.info("HistAuditService getList |{}",histAudit);
        return histAuditDao.list(histAudit);
    }

    public void addHistAudit(HistAudit histAudit){
        log.info("HistAuditService addHistAudit |{}",histAudit);
        histAuditDao.addHistAudit(histAudit);
    }
}
