package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.OrderDto;
import com.qingAn.reggie.entity.Orders;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
@Api(value = "/order", tags = "订单控制器")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 作用： 下单
     *
     * @param orders
     * @param session
     * @return
     */
    @ApiOperation("下单")
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders, HttpSession session) {
        //1.得到登陆者
        User user = (User) session.getAttribute("user");
        //下单
        orderService.submit(orders, user.getId());
        return R.success("下单成功");
    }

    @ApiOperation("订单")
    @GetMapping("/page")
    public R<Page<Orders>> pagingQuery(int page, int pageSize, String number, @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginTime,@DateTimeFormat(pattern = "yyyy-MM-dd")  Date endTime) {
        return orderService.pagingQuery(page, pageSize, number, beginTime, endTime);
    }

    @ApiOperation("用户端订单")
    @GetMapping("/userPage")
    public R<Page<OrderDto>> userPage(int page, int pageSize , HttpSession session){
        return orderService.userPage(page,pageSize,session);
    }
}
