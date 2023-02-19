package com.example.keycloakdemo.controller;


import com.example.keycloakdemo.form.*;
import com.example.keycloakdemo.vo.PageResult;
import com.example.keycloakdemo.dao.model.SpaceNodeInfo;
import com.example.keycloakdemo.service.SpaceNodeService;
import com.example.keycloakdemo.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : NodeAction
 * @Description : 空间节点控制层
 * @Date: 2022-07-14 16:01
 */
@Slf4j
@RestController
@RequestMapping(value = "/node")
@Api(value = "NodeAction", tags = {"空间节点相关"})
public class NodeAction {

    private final SpaceNodeService spaceNodeService;

    public NodeAction(SpaceNodeService spaceNodeService) {
        this.spaceNodeService = spaceNodeService;
    }

    @ApiOperation(value = "获取空间节点列表", notes = "nodeList")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResult<SpaceNodeInfo> nodeList(@RequestBody @Validated NodeListForm nodeListForm) {
        log.info(" nodeList request param | {}", nodeListForm);
        return spaceNodeService.nodeList(nodeListForm);
    }

    @ApiOperation(value = "获取所有空间节点列表", notes = "nodeList")
    @RequestMapping(value = "/allList", method = RequestMethod.POST)
    public ResponseEntity<List<SpaceNodeInfo>> allNodeList(@RequestBody AllNodeListForm allNodeListForm) {
        log.info(" allNodeList request param | {}", allNodeListForm);
        List<SpaceNodeInfo> spaceNodeInfos = spaceNodeService.allNodeList(allNodeListForm);
        return new ResponseEntity<>(spaceNodeInfos);
    }

    @ApiOperation(value = "添加空间节点", notes = "addNodeInfo")
    @RequestMapping(value = "/addNode", method = RequestMethod.POST)
    public void addNodeInfo(@RequestBody @Validated AddSpaceNodeForm addSpaceNodeForm) {
        log.info(" addNodeInfo request param | {}", addSpaceNodeForm);
        spaceNodeService.addNodeInfo(addSpaceNodeForm);
    }

    @ApiOperation(value = "修改空间节点名称", notes = "updateNodeName")
    @RequestMapping(value = "/updateNodeName", method = RequestMethod.POST)
    public void updateNodeName(@RequestBody @Validated UpdateNodeForm updateNodeForm) {
        log.info(" updateNodeName request param | {}", updateNodeForm);
        spaceNodeService.updateNodeName(updateNodeForm);
    }

    @ApiOperation(value = "删除空间节点", notes = "deleteNode")
    @RequestMapping(value = "/deleteNode", method = RequestMethod.POST)
    public void deleteNode(@RequestBody @Validated DeleteNodeForm deleteNodeForm) {
        log.info(" deleteNode request param | {}", deleteNodeForm);
        spaceNodeService.deleteNode(deleteNodeForm.getId());
    }

    @ApiOperation(value = "批量添加空间节点", notes = "addBatchNode")
    @RequestMapping(value = "/addBatchNode", method = RequestMethod.POST)
    public void addBatchNode(@RequestBody @Validated AddBatchNodeForm addBatchNodeForm) {
        log.info(" addBatchNode request param | {}", addBatchNodeForm);
        spaceNodeService.addBatchNode(addBatchNodeForm);
    }

    @ApiOperation(value = "导入节点信息", notes = "importNode")
    @RequestMapping(value = "/importNode", method = RequestMethod.POST)
    public void importNode(ImportNodeForm importNodeForm) throws Exception {
        log.info(" importNode request param | {}", importNodeForm);
        Assert.notNull(importNodeForm, "导入文件信息不能为空");
        spaceNodeService.importNode(importNodeForm);
    }

    @ApiOperation(value = "导出节点信息", notes = "exportNode")
    @RequestMapping(value = "/exportNode", method = RequestMethod.POST)
    public void exportNode(@ApiParam(value = "企业id", name = "eid") @RequestParam("eid") Long eid,
                           @ApiParam(value = "空间集id", name = "spaceSetId") @RequestParam("spaceSetId") Long spaceSetId,
                           HttpServletResponse response, HttpServletRequest request) throws Exception {
        ExportNodeForm exportNodeForm = ExportNodeForm.builder().eid(eid).spaceId(spaceSetId).build();
        log.info(" exportNode request param | {}", exportNodeForm);
        spaceNodeService.exportNodeInfo(exportNodeForm, response, request);
    }


}
