package com.ww.springcloud.dao;


import com.ww.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-10-12 09:35
 * @describe:
 */

@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
