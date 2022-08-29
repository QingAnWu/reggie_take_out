package com.qingAn.reggie.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingAn
 */
@Data
public class DishDto extends Dish {
    /**
     * 口味
     */
    private List<DishFlavor> flavors = new ArrayList<>();

    /**
     * 菜品分类名称
     */
    private String categoryName;

    private Integer copies;
}
