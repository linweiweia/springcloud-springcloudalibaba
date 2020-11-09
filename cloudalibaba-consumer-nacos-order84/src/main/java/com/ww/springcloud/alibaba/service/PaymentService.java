package com.ww.springcloud.alibaba.service;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 13:59
 * @describe:    本工程就是用ribbon和feign(负载均衡)去结合sentinel
 *  *            已经blockHandler和fallbackHandler的全局处理
 *               feign结合sentinel
 */

@FeignClient(value = "nacos-payment-provider")
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);

}
