package com.ww.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-28 15:35
 * @describe:
 */

@SpringBootApplication
//@EnableEurekaClient 这里是不需要注册进Eureka真的微服务是要注册进去的
public class ConfigClientMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class, args);
    }
}
