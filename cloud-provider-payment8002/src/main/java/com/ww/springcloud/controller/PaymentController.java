package com.ww.springcloud.controller;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import com.ww.springcloud.service.PaymentService;
import com.ww.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 14:24
 * @describe:
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 区分集群服务器端口号
     */
    @Value("${server.port}")
    private String serverPort;



    /**
     * 插入发票
     * @param payment 发票信息
     * @return 同一结果
     */
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功了，ServerPort:" + serverPort, result);
        } else {
            return new CommonResult(400, "插入数据库失败", null);
        }
    }



    /**
     * 查询发票
     * @param id 发票ID
     * @return 同一结果
     */
    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询数据成功了，ServerPort:" + serverPort, payment);
        } else {
            return new CommonResult(400, "没有对应记录,查询ID: " + id, null);
        }
    }




}
