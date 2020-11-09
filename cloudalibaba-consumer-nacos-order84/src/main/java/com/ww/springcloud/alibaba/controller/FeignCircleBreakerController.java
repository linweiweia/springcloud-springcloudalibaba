package com.ww.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ww.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.ww.springcloud.alibaba.myhandler.CustomerFallbackHandler;
import com.ww.springcloud.alibaba.service.PaymentService;
import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 10:07
 * @describe:   本工程就是用feign(负载均衡)去结合sentinel
 */

@RestController
@Slf4j
public class FeignCircleBreakerController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping(value = "/consumer/feign_sentinel_blockHandler_fallback/{id}")
    @SentinelResource(value = "feign_sentinel_blockHandler_fallback",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = CustomerFallbackHandler.class,
            fallback = "fallbackHandler")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        CommonResult<Payment> payment = paymentService.paymentSQL(id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (payment.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return payment;
    }

}
