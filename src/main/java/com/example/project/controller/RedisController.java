package com.example.project.controller;


import com.example.project.util.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RedisController
 * @description:
 * @author: sh.Liu
 * @date: 2022-03-08 14:28
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private final RedisTemplate redisTemplate;
    private final RedisUtil redisUtil;

    public RedisController(RedisTemplate redisTemplate,
                           RedisUtil redisUtil) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = redisUtil;
    }

    @GetMapping("save")
    public String save(String key, String value){
        redisUtil.set(key,value);
        String redisValue =(String) redisUtil.get(key);
        String value1 =(String) redisUtil.get(value);
        return redisValue+"/" +value1;
    }

}
