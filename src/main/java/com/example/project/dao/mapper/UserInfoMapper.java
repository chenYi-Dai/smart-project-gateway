package com.example.project.dao.mapper;

import com.example.project.dao.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    void add(User user);

    User getUser(@Param(value = "id") String id);

    User getUserByName(@Param(value = "name") String name);

    void deleteUser(@Param(value = "id") Long id);
}
