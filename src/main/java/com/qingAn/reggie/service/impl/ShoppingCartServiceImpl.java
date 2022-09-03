package com.qingAn.reggie.service.impl;

import com.qingAn.reggie.entity.ShoppingCart;
import com.qingAn.reggie.mapper.ShoppingCartMapper;
import com.qingAn.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 作用：添加购物车
     *
     * @param shoppingCart 当前提交的购物项的数据
     * @param uId 用户id
     */
    @Override
    public void addShoppingCart(ShoppingCart shoppingCart,Long uId) {
        ShoppingCart uidAndDidOrSid = shoppingCartMapper.findUidAndDidOrSid(shoppingCart);
        shoppingCart.setUserId(uId);
        if (uidAndDidOrSid==null){
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.addShoppingCart(shoppingCart);
        }else {
            shoppingCart.setNumber(shoppingCart.getNumber()+1);
            shoppingCartMapper.update(shoppingCart);
        }
    }

    /**
     * 根据用户id查看购物车
     * @param id
     * @return
     */
    @Override
    public List<ShoppingCart> findCartByUserId(Long id) {
        List<ShoppingCart>  shoppingCartList =   shoppingCartMapper.findCartByUserId(id);
        return shoppingCartList;
    }

    /**
     * 清空购物车
     * @param userId
     */
    @Override
    public void clean(Long userId) {
        shoppingCartMapper.clean(userId);
    }
}