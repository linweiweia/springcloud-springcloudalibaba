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

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果>>>>>" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功了，ServerPort:" + serverPort, result);
        } else {
            return new CommonResult(400, "插入数据库失败", null);
        }
    }


    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果>>>>>>" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询数据成功了，ServerPort:" + serverPort, payment);
        } else {
            return new CommonResult(400, "没有对应记录,查询ID: " + id, null);
        }
    }


    /**
     * 服务发现
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        //获取服务
        List<String> services = discoveryClient.getServices();
        for (String service: services ) {
            log.info("服务名称" + service);
        }
        //获取指定服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()
                    + "\t" + instance.getHost()
                    + "\t" + instance.getPort()
                    + "\t" + instance.getUri());
            //CLOUD-PAYMENT-SERVICE(ID名称)	192.168.137.1（主机地址）	8001	http://192.168.137.1:8001
        }
        return this.discoveryClient;
    }

}
