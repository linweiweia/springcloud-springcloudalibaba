package com.ww.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-30 14:38
 * @describe:
 */

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //允许负载均衡nacos中的ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
