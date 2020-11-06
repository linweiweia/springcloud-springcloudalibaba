package com.ww.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-05 17:26
 * @describe:  这里测试用例为流量控制 服务降级 热点限流
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

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-------热点数据测试--------";
    }
    //只有瞒住sentinel的限制规则才会走这个兜底的方法，如果是java程序错误就不会走这个方法。
    //必须写BlockException这个参数
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return " I am fallback function";
    }


}
