package com.ww.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-27 10:09
 * @describe:
 */

@SpringBootApplication
//@EnableEurekaClient 这里是不需要注册进Eureka真的微服务是要注册进去的
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
