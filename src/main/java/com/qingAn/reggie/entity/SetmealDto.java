package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("套餐与分类名称")
public class SetmealDto extends Setmeal implements Serializable {

    private static final long serialVersionUID = -3342615395947028823L;
    /**
     * 套餐关联的菜品集合
     */
    @ApiModelProperty("套餐关联的菜品集合")
    private List<SetmealDish> setmealDishes;
    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String categoryName;
}