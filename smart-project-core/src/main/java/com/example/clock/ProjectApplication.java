package com.example.clock;

import com.example.clock.annotation.TestAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(value = {"com.example.clock.dao.mapper"})
//@EnableDiscoveryClient
//@TestAnnotation
@EnableFeignClients
public class ProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectApplication.class, args);
        String[] beanNames = run.getBeanDefinitionNames();

        // 打印每个Bean的名称和对应的类型
        for (String beanName : beanNames) {
            Object bean = run.getBean(beanName);
            System.out.println("Bean名称: " + beanName + ", 类型: " + bean.getClass().getName());
        }
    }

}
