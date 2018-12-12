package com.feign.practice.FeignClient;

import feign.Contract;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfiguration {

    //This replaces the SpringMvcContract with feign.Contract.Default
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }

    @Bean
    public Decoder feignDecoder(){
        return new feign.codec.Decoder.Default();
    }

    @Bean
    public Encoder feignEncoder(){
        return new feign.codec.Encoder.Default();
    }

//    @Bean
//    public Logger feignErrorLogger(){
//        return new feign.Logger.ErrorLogger();
//    }

    //If we create both @Configuration bean and configuration properties in application.yml,
    //configuration properties will win. It will override @Configuration values. But if you want to change the priority to @Configuration, you can change feign.client.default-to-properties to false.



    /*
    Spring Cloud creates a new ensemble as an ApplicationContext on demand for each named client using FeignClientsConfiguration.
    This contains (amongst other things) an feign.Decoder, a feign.Encoder, and a feign.Contract.
        */
}

/*
    Spring Cloud Netflix provides the following beans by default for feign (BeanType beanName: ClassName):

    Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)
    Encoder feignEncoder: SpringEncoder
    Logger feignLogger: Slf4jLogger
    Contract feignContract: SpringMvcContract
    Feign.Builder feignBuilder: HystrixFeign.Builder
    Client feignClient: if Ribbon is enabled it is a LoadBalancerFeignClient, otherwise the default feign client is used

    Spring Cloud Netflix does not provide the following beans by default for feign,
    but still looks up beans of these types from the application context to create the feign client:

    Logger.Level
    Retryer
    ErrorDecoder
    Request.Options
    Collection<RequestInterceptor>
    SetterFactory
    Creating a bean of one of those type and placing it in a @FeignClient configuration
 */
