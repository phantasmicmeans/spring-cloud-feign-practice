package com.feign.practice.FeignClientEureka;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaConfiguration {

    @Bean
    public EurekaInstance eurekaInstance(){
        return new EurekaInstance();
    }
}
