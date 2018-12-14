package com.feign.practice.FeignClientEureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "eureka-client")
public interface ClientEureka { // use default feign client

    @GetMapping(value = "/eureka/actuator/info")
    ResponseEntity getDetailMyClientFromEureka(); //필요한 것들만 모아서 출력 시킴, 자기 자신

    @GetMapping(value = "/eureka/applications/info") //(o) 유레카에 등록된 모든 어플리케이션 - 명세는 간단히
    ResponseEntity getApplicationsFromEureka();

    @GetMapping(value = "/eureka/application/{applicationName}/info", produces = "application/json") // (o) applications들 중 이름으로 application 명세, 간단히
    ResponseEntity getApplicationFromEureka(@PathVariable String applicationName);
    //AppName, Host, Id, IpAddr, Status

    @GetMapping(value = "/eureka/applications/{applicationName}/info/detail", produces = "application/json") //eureka instanceId로 명세 찾기
    ResponseEntity getApplicationDetailFromEureka(@PathVariable("applicationName") String applicationName);
    //applicationName으로 모든 data 찾기, applicationName 중복 가능? <- 확인 필요, Applications 가 아니고,Application임

    @GetMapping(value = "/eureka/regions/info", produces = "application/json")
    ResponseEntity getAnyRegionsFromEureka();
    // 모든 리전 명세

    @GetMapping(value = "/eureka/regions/{regions}/applications/info", produces = "application/json")
    ResponseEntity getApplicationsForRegions(@PathVariable("regions") String regions);

}
