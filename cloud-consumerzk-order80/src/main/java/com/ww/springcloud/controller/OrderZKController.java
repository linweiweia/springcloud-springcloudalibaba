package com.ww.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-16 11:02
 * @describe:
 */

@RestController
@Slf4j
public class OrderZKController {

    private static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String getZKPayment() {
        String payment = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
        return payment;
    }

}
