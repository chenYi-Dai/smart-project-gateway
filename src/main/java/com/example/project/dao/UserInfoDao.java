package com.example.project.dao;

import com.example.project.dao.mapper.UserInfoMapper;
import com.example.project.dao.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserInfoDao {

    @Resource
    private UserInfoMapper userInfoMapper;

    public User getUserInfo(String id){
        return userInfoMapper.getUser(id);
    }

    public User getUserInfoByName(String name){
        return userInfoMapper.getUserByName(name);
    }


    public void add(User user){
        userInfoMapper.add(user);
    }

    public void deleteById(Long id){
        userInfoMapper.deleteUser(id);
    }

}
