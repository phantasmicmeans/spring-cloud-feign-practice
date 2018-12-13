package com.feign.practice.FeignClientEureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "eureka-client")
public interface ClientEureka { // use default feign client

    @GetMapping(value = "/actuator/info")
    ResponseEntity getDetailFromEureka(); //필요한 것들만 모아서 출력 시킴.

    @GetMapping(value = "/eureka/application")
    ResponseEntity getApplicationsFromEureka();

    @GetMapping(value = "/eureka/applications/{applicationName}", produces = "application/json") //applications들 중 이름으로 application 명세
    ResponseEntity getApplicationWithApplicationName(@PathVariable String applicationName);

    @GetMapping(value = "/eureka/instances/{instanceId}", produces = "application/json") //eureka instanceId로 명세 찾기
    ResponseEntity getInstanceDetailFromEureka(@PathVariable("instanceId") String instanceId);

    @GetMapping(value = "/eureka/regions/clients", produces = "application/json") //모든 리전 명세
    ResponseEntity getAnyRegionsFromEureka();

    @GetMapping(value = "/eureka/regions/{regions}/clients/applications", produces = "application/json")
    ResponseEntity getApplicationsForRegions(@PathVariable("regions") String regions);

}
