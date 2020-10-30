package com.ww.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.stereotype.Component;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-26 12:20
 * @describe:
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是消费方服务降级方法，并且没有和业务代码混合在一起。";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "我是消费方服务降级方法，并且没有和业务代码混合在一起。";
    }
}
