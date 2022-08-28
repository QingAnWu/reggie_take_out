package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.DishDto;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author qingAn
 * @date 2022/08/28 23:34
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;


    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto, HttpSession session) {
        //1. 获取当前登陆者,补全创建者与更新者信息
        Employee employee = (Employee) session.getAttribute("employee");
        dishDto.setCreateUser(employee.getId());
        dishDto.setUpdateUser(employee.getId());
        //2. 保存菜品与口味
        dishService.save(dishDto);
        return R.success("保存菜品成功");
    }
}
