package com.ww.springcloud.serivce;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-25 17:17
 * @describe:
 */

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "    paymentInfo_OK, id " + id + "  O(∩_∩)O哈哈~";
    }

    //程序出错、请求超时、线程池满了、服务熔断都会引起服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_Timeout(Integer id) {
        //int i = 1 / 0;
        //超时不使用sleep来测试，因为会打断线程，用高jmeter高并发来打这个接口，造成超时的情况。看IDEA的日志，会出现一两条的超时兜底
        return "线程池：" + Thread.currentThread().getName() + "    paymentInfo_Timeout, id " + id + "  ┭┮﹏┭┮呜呜";
    }

    //服务降级兜底方法
    public String paymentInfo_TimeoutHandle(Integer id) {
        return "我是服务端服务降低的兜底方法 " + " 线程池：" + Thread.currentThread().getName() + "  服务器繁忙，请稍后再试。";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "PaymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String PaymentCircuitBreaker(Integer id) {
        if (id < 0) {
            //就是程序错误，会走服务降级方法
            throw new RuntimeException("ID不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return "我是提供方8001的服务熔断方法" + serialNumber;
    }
    public String PaymentCircuitBreaker_fallback(Integer id) {
        return "我是提供方8001的服务熔断方法的服务降级方法";
    }



}
