package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Orders;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Api(value = "/order" ,tags = "订单控制器")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

 
    /**
     * 作用： 下单
     * @param orders
     * @param session
     * @return
     */
    @ApiOperation("下单")
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders, HttpSession session){
        //1.得到登陆者
        User user = (User) session.getAttribute("user");
        //下单
        orderService.submit(orders,user.getId());
        return R.success("下单成功");
    }
}
