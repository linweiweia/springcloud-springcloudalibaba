package com.ww.springcloud.alibaba.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ww.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-25 22:53
 * @describe:
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_GlobalFallbackMethod")
public class PaymentHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    //ok方法的服务降级在PaymentFallBackService中做相当于特殊除了
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    //这里因为超时消费方做服务降级，要演示出来需要服务端sleep下，不然因为服务端已经做了服务降级1s以内马上就返回了消费方不会做服务降低。
    //@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    //})

    //不写兜底方法名默认是全局兜底方法
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        int i = 1 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    //服务降低兜底方法
    public String paymentInfo_TimeoutHandle(@PathVariable("id")Integer id) {
        return "我是客户端的服务降级兜底方法" + " 线程池：" + Thread.currentThread().getName() + "  服务器繁忙，请稍后再试。";
    }

    //全局同一兜底方法
    public String payment_GlobalFallbackMethod() {
        return "我是消费方的全局兜底方法" + "线程池：" + Thread.currentThread().getName() + "  服务器繁忙，请稍后再试。";
    }


}
