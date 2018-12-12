package com.feign.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PracticeApplication {
/*
'll introduce and explain Feign, a declarative Http client developed by Netflix
Feign aims at simplifying HTTP API clients. Simply put, the developers needs only to declare and
annotate an interface while the actual implementation will be provisioned at runtime.
 */
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
