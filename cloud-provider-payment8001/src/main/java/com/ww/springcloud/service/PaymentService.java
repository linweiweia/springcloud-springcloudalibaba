package com.ww.springcloud.service;

import com.ww.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 14:21
 * @describe:
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
