package com.example.keycloakdemo.controller;

import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.dao.model.SpaceNodeInfo;
import com.example.keycloakdemo.form.CustomerInfoFrom;
import com.example.keycloakdemo.form.NodeListForm;
import com.example.keycloakdemo.service.CustomerInfoService;
import com.example.keycloakdemo.service.imple.LdapService;
import com.example.keycloakdemo.service.imple.SpaceNodeService;
import com.example.keycloakdemo.vo.PageResult;
import com.example.keycloakdemo.vo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.NamingException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/demo")
@ApiModel(value = "DemoController")
public class DemoController {


    @Resource
    private LdapService ldapService;

    @Resource
    SpaceNodeService  spaceNodeService;


    @GetMapping("getValue")
    @ApiOperation(value = "ce")
    public String getValue(){
        log.info("heihei");
        return ldapService.test();
    }

    @GetMapping("add")
    public void add() throws NamingException {
        ldapService.createOnePerson(User.builder()
                .email("564217869@qq.com")
                .firstName("姓")
                .name("姓名")
                .secondName("名")
                .userName("yonghu")
                .password("123456")
                .build());
    }

    @GetMapping("get")
    public void getByUserName(){
        User yonghu = ldapService.getPersonDetail("yonghu");
        log.info(" yonghu | {}",yonghu);
    }

    @GetMapping("delete")
    public void deleteUser(){
        ldapService.removeOnePerson("yonghu");
        User yonghu = ldapService.getPersonDetail("yonghu");
        log.info(" yonghu | {}",yonghu);
    }


}
