package com.ww.springcloud.serivce;

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

    public String paymentInfo_Timeout(Integer id) {
        int timeout = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "    paymentInfo_Timeout, id " + id + "  ┭┮﹏┭┮呜呜";
    }

}
