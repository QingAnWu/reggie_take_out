package com.qingAn.reggie.entity;

import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    /**
     * 套餐关联的菜品集合
     */
    private List<SetmealDish> setmealDishes;
    /**
     * 分类名称
     */
    private String categoryName;
}