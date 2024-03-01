package com.example.clock.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.clock.form.SpaceListForm;
import com.example.clock.vo.PageParam;
import com.example.clock.vo.ResponseEntity;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "CustomerController", tags = {"客户信息"})
@RequestMapping(value = "/smart")
public class TestController {

    @RequestMapping(value = "/testPost",method = RequestMethod.POST)
    public ResponseEntity testPostParam(@RequestBody PageParam<SpaceListForm> pageParam){
        log.info("testPostParam | {}", JSONObject.toJSONString(pageParam));
        return new ResponseEntity(pageParam);
    }

    @RequestMapping(value = "/testGetParam",method = RequestMethod.GET)
    public ResponseEntity testGetParam(@RequestParam(value = "param") String param){
        log.info("testPostParam | {}", JSONObject.toJSONString(param));
        return new ResponseEntity(param);
    }
}
