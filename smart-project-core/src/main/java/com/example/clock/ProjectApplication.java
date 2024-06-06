package com.example.clock;

import com.example.clock.annotation.TestAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(value = {"com.example.clock.dao.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
//@TestAnnotation
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
