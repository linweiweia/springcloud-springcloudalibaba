package com.ww.springcloud.controller;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 15:50
 * @describe:
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";

    //这个请求地址是eureka的服务名8001和8002服务提供，本服务用CLOUD-PAYMENT-SERVICE服务名轮询获取不同的ip地址，实现集群提供服务
    //底层还是使用http请求，只是去eureka服务端用CLOUD-PAYMENT-SERVICE服务名获取真正的请求地址
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求所以这个Controller不要写@RequestBody
     * @param payment
     * @return
     */
    @GetMapping("/order/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/order/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}
