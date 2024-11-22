package com.example.clock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@ConditionalOnProperty(prefix = "cebs.feign.middle.aml.stub", name = "mode", havingValue = "mock")
public class TestConfig {

    @Value("${cebs.feign.middle.aml.stub.mode}")
    private String name;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @PostConstruct
    public void test(){
        System.out.println(name);
        System.out.println(adminAddresses);
    }
}
