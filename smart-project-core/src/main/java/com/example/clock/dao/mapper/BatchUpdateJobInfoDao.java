package com.example.clock.dao.mapper;


import com.example.clock.dao.model.BatchUpdateJobInfoDto;

import java.util.List;

public interface BatchUpdateJobInfoDao {

    long insertAndGetJobID(BatchUpdateJobInfoDto jobInfo);

    int insert(BatchUpdateJobInfoDto t);
    int updateByPrimaryKeySelective(BatchUpdateJobInfoDto t);
    int updateByPrimaryKey(BatchUpdateJobInfoDto t);
    BatchUpdateJobInfoDto selectByPrimaryKey(Object id);
    List<BatchUpdateJobInfoDto> findList(BatchUpdateJobInfoDto t);
}
