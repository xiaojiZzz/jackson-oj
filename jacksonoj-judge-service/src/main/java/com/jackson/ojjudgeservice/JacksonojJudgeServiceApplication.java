package com.jackson.ojjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author jackson
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jackson.ojserviceclient.service"})
@ComponentScan("com.jackson")
public class JacksonojJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacksonojJudgeServiceApplication.class, args);
    }

}
