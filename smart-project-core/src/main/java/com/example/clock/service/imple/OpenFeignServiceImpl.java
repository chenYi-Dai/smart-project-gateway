/*
package com.example.clock.service.imple;

import com.example.clock.dao.SpaceNodeDao;
import com.example.clock.dao.model.SpaceNodeInfo;
import com.example.clock.vo.SpaceNodeVO;
import com.ruoyi.common.entry.ResponseEntity;
import com.ruoyi.common.feignService.OpenFeignService;
import com.example.clock.form.NodeListForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class OpenFeignServiceImpl implements OpenFeignService {
    @Resource
    private SpaceNodeDao spaceNodeDao;

    @Override
    public ResponseEntity queryOneData(NodeListForm nodeListForm) throws Exception {
        log.info("OpenFeignServiceImpl queryOneData request | {}",nodeListForm);
        SpaceNodeVO spaceNodeVO = spaceNodeDao.nodeInfoById(nodeListForm.getSpaceSetId());
        throw new Exception("cuowu");
    }

    @Override
    public ResponseEntity queryAllData(NodeListForm nodeListForm) throws Exception {
        log.info("OpenFeignServiceImpl queryAllData request | {}",nodeListForm);
        List<SpaceNodeInfo> list = spaceNodeDao.list(SpaceNodeInfo.builder().build());
        throw new Exception("cuowu");
    }
}
*/
