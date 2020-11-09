package com.ww.springcloud.alibaba.dao;

import com.ww.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author linweiwei
 * @version 1.0
 * @date 2020-11-09 18:16
 * @describe:
 */

@Mapper
public interface OrderDao {

    //1.创建订单
    void create(Order order);

    //2.改变订单状态（从初始值0改为成功1）
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
