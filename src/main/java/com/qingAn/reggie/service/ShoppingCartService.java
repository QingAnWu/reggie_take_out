package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 作用：添加购物车
     *
     * @param shoppingCart 当前提交的购物项的数据
     * @param uId 用户id
     */
    void addShoppingCart(ShoppingCart shoppingCart, Long uId);

    /**
     * 根据用户id查看购物车
     * @param id
     * @return
     */
    List<ShoppingCart> findCartByUserId(Long id);

    /**
     * 清空购物车
     * @param userId
     */
    void clean(Long userId);
}