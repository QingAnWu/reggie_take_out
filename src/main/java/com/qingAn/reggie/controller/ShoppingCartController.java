package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.ShoppingCart;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车
 * @author 吴
 */
@Slf4j
@RestController
@Api(value = "/shoppingCart",tags = "购物车控制器")
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 作用：添加购物车
     * @param shoppingCart   当前提交的购物项的数据
     * @param session
     * @return
     */
    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart, HttpSession session) {
        User user = (User) session.getAttribute("user");
        shoppingCartService.addShoppingCart(shoppingCart, user.getId());
        return R.success("添加成功");
    }

    /**
     *
     * 作用： 查看购物车
     * @param session
     * @return
     */
    @ApiOperation("查看购物车")
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(HttpSession session){
        //1. 获取当前登陆者
        User user = (User) session.getAttribute("user");
        //2.根据当前登陆者查看购物车
        List<ShoppingCart> shoppingCartList = shoppingCartService.findCartByUserId(user.getId());
        return R.success(shoppingCartList);
    }

    /**
     * 作用：清空购物车
     * @param session
     * @return
     */
    @ApiOperation("清空购物车")
    @DeleteMapping("/clean")
    public R<String> clean(HttpSession session){
        //1. 获取当前登陆者
        User user = (User) session.getAttribute("user");
        //2. 清空购物车
        shoppingCartService.clean(user.getId());
        return R.success("清空购物车成功");
    }

}