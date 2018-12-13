package com.feign.practice.FeignClientEureka;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;

public class EurekaHystrixFallback implements FallbackFactory<ClientEureka> {
    //fallback eureka-client

    @Override
    public ClientEureka create(Throwable throwable) {
        return new ClientEureka() {

            @Override
            public ResponseEntity getDetailFromEureka() {
                return null;
            }

            @Override
            public ResponseEntity getApplicationsFromEureka() {
                return null;
            }

            @Override
            public ResponseEntity getApplicationWithApplicationName(String applicationName) {
                return null;
            }

            @Override
            public ResponseEntity getInstanceDetailFromEureka(String instanceId) {
                return null;
            }

            @Override
            public ResponseEntity getAnyRegionsFromEureka() {
                return null;
            }

            @Override
            public ResponseEntity getApplicationsForRegions(String regions) {
                return null;
            }
        };
    }
}
