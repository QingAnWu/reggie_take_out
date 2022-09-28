package com.qingAn.reggie.service;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Orders;
import com.qingAn.reggie.entity.Page;

import javax.servlet.http.HttpSession;
import java.util.Date;

public interface OrderService {

    /**
     * 作用： 下单
     * @param orders  订单的信息（地址id，支付方式，备注）
     * @Param userId  当前的登陆者
     * @return
     */
    void submit(Orders orders, Long userId);

    R<Page<Orders>> pagingQuery(int page, int pageSize, String number, Date beginTime, Date endTime);

    R<Page<Orders>> userPage(int page, int pageSize, HttpSession session);
}