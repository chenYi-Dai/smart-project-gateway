package com.example.keycloakdemo.controller;

import com.example.keycloakdemo.service.LdapService;
import com.example.keycloakdemo.vo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.NamingException;

@Slf4j
@RestController
@RequestMapping("/demo")
@ApiModel(value = "DemoController")
public class DemoController {


    @Resource
    private LdapService ldapService;

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
