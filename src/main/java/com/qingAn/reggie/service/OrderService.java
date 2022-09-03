package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.Orders;

public interface OrderService {

    /**
     * 作用： 下单
     * @param orders  订单的信息（地址id，支付方式，备注）
     * @Param userId  当前的登陆者
     * @return
     */
    void submit(Orders orders, Long userId);
}