package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper  {

    /**
     * 根据id查询是否存在
     * @param shoppingCart
     * @return
     */
    ShoppingCart findUidAndDidOrSid(ShoppingCart shoppingCart);

    /**
     * 添加购物车项
     * @param shoppingCart
     */
    void addShoppingCart(ShoppingCart shoppingCart);

    /**
     * 更新购物项
     * @param shoppingCartOne
     */
    @Update("update shopping_cart set number=#{number} where id=#{id}")
    void update(ShoppingCart shoppingCartOne);

    /**
     * 根据用户id查看购物车
     * @param id
     * @return
     */
    @Select("select * from shopping_cart where user_id=#{userId}")
    List<ShoppingCart> findCartByUserId(Long userId);

    /**
     * 清空购物车
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void clean(Long userId);
}