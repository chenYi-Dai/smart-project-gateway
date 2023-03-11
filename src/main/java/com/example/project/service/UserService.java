package com.example.project.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.project.config.SmartProjectBusinessException;
import com.example.project.dao.UserInfoDao;
import com.example.project.dao.model.User;
import com.example.project.form.AddUserForm;
import com.example.project.form.LoginInfoForm;
import com.example.project.shiro.JWTToken;
import com.example.project.util.JWTUtils;
import com.example.project.util.MD5Util;
import com.example.project.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {

    private final static String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    @Resource
    private UserInfoDao userInfoDao;

    public UserVO getUserInfo(String userName) {
        User userInfo = userInfoDao.getUserInfoByName(userName);
        UserVO build = null;
        if(!ObjectUtil.isEmpty(userInfo)){
            build = UserVO.builder()
                    .id(userInfo.getId())
                    .name(userInfo.getName())
                    .password(userInfo.getPassword())
                    .remark(userInfo.getEmail())
                    .createUser(userInfo.getCreateUser())
                    .phone(userInfo.getPhone())
                    .build();
        }
        return build;
    }

    public void addUserInfo(AddUserForm addUserForm) {

        String userId = RandomStringUtils.randomAlphanumeric(10);

        String password = addUserForm.getPassword();
        String passToDBPass = MD5Util.inputPassToDBPass(password);
        userInfoDao.add(User.builder()
                .userId(userId)
                .email(addUserForm.getEmail())
                .password(passToDBPass)
                .name(addUserForm.getName())
                .createUser(addUserForm.getCreateUser())
                .build());
    }

    public String login(LoginInfoForm loginInfoForm){
        String formPassword = MD5Util.inputPassToDBPass(loginInfoForm.getPassword());
        String token;
        User info = userInfoDao.getUserInfoByName(loginInfoForm.getName());
        if(ObjectUtil.isEmpty(info)){
            throw new SmartProjectBusinessException("用户信息不存在");
        }
        if (!info.getPassword().equals(formPassword)){
            throw new SmartProjectBusinessException("密码不正确");
        }
        token = JWTUtils.sign(info.getName(), info.getPassword());
        JWTToken jwtToken = new JWTToken(token);
        try{
            SecurityUtils.getSubject().login(jwtToken);
        }catch (Exception e){
            throw new SmartProjectBusinessException("登录失败");
        }
        return token;
    }
}
