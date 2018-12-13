package com.feign.practice;

import com.netflix.appinfo.AmazonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PracticeApplication {
/*
'll introduce and explain Feign, a declarative Http client developed by Netflix
Feign aims at simplifying HTTP API clients. Simply put, the developers needs only to declare and
annotate an interface while the actual implementation will be provisioned at runtime.
 */

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port:8081}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);

    }

    @Bean
    @Profile("develop")
    public EurekaInstanceConfigBean eurekaInstanceConfigBeanAWS(InetUtils inetUtils){
        EurekaInstanceConfigBean bean = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        logger.debug(info.getName().toString());
        bean.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
        bean.setIpAddress(info.get(AmazonInfo.MetaDataKey.localIpv4));
        bean.setNonSecurePort(port);
        bean.setDataCenterInfo(info);
        return bean;
    }

}
