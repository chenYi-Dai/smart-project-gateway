package com.example.clock.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.clock.form.DeleteSpaceListForm;
import com.example.clock.form.SpaceListForm;
import com.example.clock.form.UpdateSpaceSetForm;
import com.example.clock.service.imple.SpaceSetService;
import com.example.clock.vo.PageParam;
import com.example.clock.vo.PageResult;
import com.example.clock.vo.ResponseEntity;
import com.example.clock.vo.SpaceSetVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ex_yi.chen
 * @ClassName : SpaceSetAction
 * @Description : 空间集相关接口
 * @Date: 2022-07-13 17:43
 */
@Slf4j
@RestController
@RequestMapping(value = "/smart/room")
@Api(value = "SpaceSetAction", tags = {"空间集相关"})
public class SpaceSetAction {

    private final SpaceSetService spaceSetService;

    public SpaceSetAction(SpaceSetService spaceSetService) {
        this.spaceSetService = spaceSetService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取空间集列表信息", notes = "SpaceSetList")
    public PageResult<SpaceSetVO> SpaceSetList(@RequestBody @Validated PageParam<SpaceListForm> pageParam) {
        log.info("spaceList request param | {}", pageParam);
        return spaceSetService.list(pageParam);
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    @ApiOperation(value = "获取空间集列表信息", notes = "SpaceSetList")
    public ResponseEntity testPost(@RequestBody @Validated PageParam<SpaceListForm> pageParam) {
        log.info("spaceList request param | {}", pageParam);
        return new ResponseEntity(JSONObject.toJSONString(pageParam));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改空间集信息", notes = "updateSpaceSet")
    public void updateSpaceSet(@RequestBody @Validated UpdateSpaceSetForm updateSpaceSetForm) {
        log.info("updateSpaceSet request param | {}", updateSpaceSetForm);
        spaceSetService.updateSpaceSet(updateSpaceSetForm);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "获取空间集信息", notes = "spaceCollectDetail")
    public ResponseEntity<SpaceSetVO> spaceSetDetail(@RequestBody @Validated DeleteSpaceListForm deleteSpaceListForm) {
        log.info("spaceCollectDetail request param | {}", deleteSpaceListForm);
        SpaceSetVO spaceSetVO = spaceSetService.spaceSetDetail(deleteSpaceListForm);
        return new ResponseEntity<>(spaceSetVO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除空间集信息", notes = "deleteSpaceList")
    public void deleteSpaceCollect(@RequestBody @Validated DeleteSpaceListForm deleteSpaceListForm) {
        log.info("deleteSpaceCollect request param | {}", deleteSpaceListForm);
        spaceSetService.deleteSpaceSet(deleteSpaceListForm.getId());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加空间集信息", notes = "deleteSpaceList")
    public void addSpaceCollect(@RequestBody UpdateSpaceSetForm updateSpaceSetForm) {
        log.info("deleteSpaceCollect request param | {}", updateSpaceSetForm);
        spaceSetService.addSpaceSet(updateSpaceSetForm);
    }


}
