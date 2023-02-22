package com.example.keycloakdemo.service;

import com.example.keycloakdemo.vo.UserVO;
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
