package com.example.project.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.project.config.SmartProjectBusinessException;
import com.example.project.form.AddUserForm;
import com.example.project.form.GetUserInfoForm;
import com.example.project.form.LoginInfoForm;
import com.example.project.service.UserService;
import com.example.project.shiro.UserRealm;
import com.example.project.vo.ResponseEntity;
import com.example.project.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody AddUserForm addUserForm) {
        log.info("UserController createUser request | {}",addUserForm);
        UserVO userInfo = userService.getUserInfo(addUserForm.getName());
        if (!ObjectUtil.isEmpty(userInfo)) {
            throw new SmartProjectBusinessException("用户已存在");
        }
        userService.addUserInfo(addUserForm);
        return new ResponseEntity();
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public ResponseEntity<UserVO> getUserInfo(@RequestBody GetUserInfoForm getUserInfoForm){
        log.info("UserController createUser getUserInfo | {}",getUserInfoForm);
        UserVO userInfo = userService.getUserInfo(getUserInfoForm.getName());
        return new ResponseEntity<>(userInfo);
    }

    @ApiOperation(value = "用户登录", notes = "login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginInfoForm loginInfoForm){
        log.info("UserController login request | {}",loginInfoForm);
        String loginToken = userService.login(loginInfoForm);
        return new ResponseEntity<>(loginToken);
    }


}
