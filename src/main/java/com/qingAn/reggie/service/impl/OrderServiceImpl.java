package com.qingAn.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.*;
import com.qingAn.reggie.exception.BusinessException;
import com.qingAn.reggie.mapper.*;
import com.qingAn.reggie.service.OrderService;
import com.qingAn.reggie.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public R<Page<Orders>> pagingQuery(int page, int pageSize, String number, Date beginTime, Date endTime) {
        PageHelper.startPage(page, pageSize);
        List<Orders> orders = orderMapper.pagingQuery(number, beginTime, endTime);
        PageInfo<Orders> ordersPageInfo = new PageInfo(orders);

        Page<Orders> objectPage = new Page<>(
                ordersPageInfo.getList(),
                ordersPageInfo.getTotal(),
                ordersPageInfo.getPageSize(),
                ordersPageInfo.getPageNum()
        );


        return R.success(objectPage);
    }

    @Override
    public R<Page<OrderDto>> userPage(int page, int pageSize, HttpSession session) {
        User user = (User) session.getAttribute("user");

        PageHelper.startPage(page, pageSize);
        List<Orders> orders = orderMapper.userPage(user.getId());

        List<OrderDto> orderDtos = orders.stream().map(
                order -> {
                    OrderDto orderDto = new OrderDto();
                    BeanUtils.copyProperties(order, orderDto);

                    List<OrderDetail> orderDetails = orderDetailMapper.queryByOrderID(order.getId());
                    orderDto.setOrderDetails(orderDetails);
                    return orderDto;
                }
        ).collect(Collectors.toList());

        PageInfo<OrderDto> ordersPageInfo = new PageInfo(orderDtos);

        Page<OrderDto> ordersPage = new Page<>();

        ordersPage.setPage(ordersPageInfo.getPageNum());
        ordersPage.setRecords(ordersPageInfo.getList());
        ordersPage.setTotal(ordersPageInfo.getTotal());
        ordersPage.setPageSize(ordersPageInfo.getPageSize());

        if (ordersPageInfo.getPageNum() < page) {
            throw new BusinessException("没有更多的数据了");
        }

        return R.success(ordersPage);
    }
}
