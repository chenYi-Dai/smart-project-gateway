package com.example.keycloakdemo.service.imple;

import com.example.keycloakdemo.dao.SpaceNodeDao;
import com.example.keycloakdemo.form.NodeListForm;
import com.example.keycloakdemo.service.OpenFeignService;
import com.example.keycloakdemo.vo.ResponseEntity;
import com.example.keycloakdemo.vo.SpaceNodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OpenFeignServiceImpl implements OpenFeignService {
    @Resource
    private SpaceNodeDao spaceNodeDao;

    @Override
    public ResponseEntity queryOneData(NodeListForm nodeListForm) {
        log.info("OpenFeignServiceImpl queryOneData request | {}",nodeListForm);
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(nodeListForm.getSpaceSetId());
        return ResponseEntity.builder().value(spaceNodeVO).build();
    }
}
