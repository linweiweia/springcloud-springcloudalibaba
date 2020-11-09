package com.ww.springcloud.alibaba.controller;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 09:45
 * @describe:  让84模块调用 验证84模块的ribbon或者feign整合sentinel
 */

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL (@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult(200, "from mysql, serverPost:" + serverPort, payment);
    }

}
