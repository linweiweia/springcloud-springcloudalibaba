package com.ww.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-25 22:40
 * @describe:
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class HystrixOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrder80.class, args);
    }
}
