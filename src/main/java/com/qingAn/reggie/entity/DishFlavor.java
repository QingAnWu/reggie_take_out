package com.qingAn.reggie.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品口味
 *
 * @author qingAn
 */
@Data
@ApiModel("菜品口味")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜品口味id")
    private Long id;
    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    private Long dishId;

    /**
     * 口味名称
     */
    @ApiModelProperty("口味名称")
    private String name;

    /**
     * 口味数据list
     */
    @ApiModelProperty("口味数据")
    private String value;

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