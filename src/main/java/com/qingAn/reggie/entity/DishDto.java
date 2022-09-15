package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingAn
 */
@Data
@ApiModel("菜品及口味")
public class DishDto extends Dish {
    /**
     * 口味
     */
    @ApiModelProperty("口味")
    private List<DishFlavor> flavors = new ArrayList<>();

    /**
     * 菜品分类名称
     */
    @ApiModelProperty("菜品分类名称")
    private String categoryName;

    @ApiModelProperty("副本")
    private Integer copies;
}
