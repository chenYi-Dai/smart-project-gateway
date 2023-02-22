package com.example.project.service;

import com.example.project.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public UserVO getUserInfo(String userId){
        return UserVO.builder()
                .name("chen")
                .phone("13798281964")
                .build();
    }
}
