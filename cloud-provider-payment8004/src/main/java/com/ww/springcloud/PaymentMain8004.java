package com.ww.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-16 10:11
 * @describe:
 */

@SpringBootApplication
@EnableDiscoveryClient  //用于向使用zookeeper和consul作为注册中心时注册于发现
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
