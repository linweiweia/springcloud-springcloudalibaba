package com.ww.springcloud.service.impl;

import com.ww.springcloud.dao.PaymentDao;
import com.ww.springcloud.entities.Payment;
import com.ww.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 14:22
 * @describe:
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
