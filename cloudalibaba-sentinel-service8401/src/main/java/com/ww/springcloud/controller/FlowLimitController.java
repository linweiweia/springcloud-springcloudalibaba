package com.ww.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-05 17:26
 * @describe:  这里测试用例为流量控制和服务降级
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        //int i = 1 / 0;
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC()
    {
        log.info(Thread.currentThread().getName()+"\t"+"限流效果 冷启动");
        return "------限流效果 冷启动-------";
    }

    @GetMapping("/testD")
    public String testD()
    {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName()+"\t"+"服务降级 RT平均响应时间");
        return "------服务降级 RT平均响应时间-------";
    }

    @GetMapping("/testE")
    public String testE()
    {
        int i = 1 / 0;
        log.info(Thread.currentThread().getName()+"\t"+"服务降级 异常比例数");
        return "------服务降级 异常比例数-------";
    }

    @GetMapping("/testF")
    public String testF()
    {
        int i = 1 / 0;
        log.info(Thread.currentThread().getName()+"\t"+"服务降级 异常数");
        return "------服务降级 异常数-------";
    }


}
