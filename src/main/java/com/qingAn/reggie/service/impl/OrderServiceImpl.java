package com.qingAn.reggie.service.impl;

import com.qingAn.reggie.entity.*;
import com.qingAn.reggie.mapper.*;
import com.qingAn.reggie.service.OrderService;
import com.qingAn.reggie.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressBookMapper addressBookMapper;


    /**
     * 作用： 下单  (本质：把购物车的数据转换为一个Orders 与多个OrderDetail)
     *
     * @param orders 订单的信息（地址id，支付方式，备注）
     * @return
     * @Param userId  当前的登陆者
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(Orders orders, Long userId) {
        //获得当前用户id, 查询当前用户的购物车数据
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.findCartByUserId(userId);

        //根据当前登录用户id, 查询用户数据
        User user = userMapper.findById(userId);

        //根据地址ID, 查询地址数据
        AddressBook addressBook = addressBookMapper.getById(orders.getAddressBookId());

        //订单的id
        Long orderId = UUIDUtils.getUUIDInOrderId().longValue();

        //声明变量记录总金额
        BigDecimal totalAmount = new BigDecimal("0");

        //遍历所有的购物像， 一个购物项就是一个订单明细， 组装订单明细数据, 批量保存订单明细
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCartList) {
            //按下alt+enter
            OrderDetail orderDetail = new OrderDetail();
            //订单项目的主键
            orderDetail.setId(UUIDUtils.getUUIDInOrderId().longValue());
            //购物项的菜品的名字
            orderDetail.setName(shoppingCart.getName());
            //订单项所属的订单的id
            orderDetail.setOrderId(orderId);
            //购物项的菜品的id
            orderDetail.setDishId(shoppingCart.getDishId());
            //购物项的套餐的id
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            //口味
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            //数量
            orderDetail.setNumber(shoppingCart.getNumber());
            //单价
            orderDetail.setAmount(shoppingCart.getAmount());
            orderDetail.setImage(shoppingCart.getImage());
            //计算每一个购物项的总价
            BigDecimal amount = shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber() + ""));
            totalAmount = totalAmount.add(amount);
            orderDetailList.add(orderDetail);
        }

        //组装订单数据, 批量保存订单数据
        orders.setId(orderId);
        //订单号
        orders.setNumber(String.valueOf(orderId));
        //状态
        orders.setStatus(1);
        orders.setUserId(userId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setAmount(totalAmount);
        orders.setUserName(user.getName());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getDetail());
        orders.setConsignee(addressBook.getConsignee());
        orders.setCheckoutTime(LocalDateTime.now());
        //插入订单
        orderMapper.save(orders);
        //批量插入订单项
        orderDetailMapper.saveBatch(orderDetailList);


        //删除当前用户的购物车列表数据
        shoppingCartMapper.clean(userId);


    }
}
