package com.jackson.ojquestionservice;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.jackson.ojquestionservice.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jackson.ojserviceclient.service"})
@ComponentScan("com.jackson")
public class JacksonojQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacksonojQuestionServiceApplication.class, args);
    }

}
