package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐菜品关系
 */
@Data
@ApiModel("套餐菜品关系")
public class SetmealDish implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("套餐菜品关系id")
    private Long id;

    /**
     * 套餐id
     */
    @ApiModelProperty("套餐id")
    private Long setmealId;

    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    private Long dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @ApiModelProperty("菜品名称")
    private String name;

    /**
     * 菜品原价
     */
    @ApiModelProperty("菜品原价")
    private BigDecimal price;

    /**
     * 份数
     */
    @ApiModelProperty("份数")
    private Integer copies;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;


    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createUser;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private Long updateUser;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Integer isDeleted;
}