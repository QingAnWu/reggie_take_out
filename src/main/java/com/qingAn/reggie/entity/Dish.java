package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 *
 * @author qingAn
 */
@Data
@ApiModel("菜品")
public class Dish implements Serializable {

    private static final long serialVersionUID = 7521084972069061879L;
    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    private Long id;

    /**
     * 菜品名称
     */
    @ApiModelProperty("菜品名称")
    private String name;

    /**
     * 菜品分类id
     */
    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    /**
     * 菜品价格
     */
    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    /**
     * 商品码
     */
    @ApiModelProperty("商品码")
    private String code;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String image;

    /**
     * 描述信息
     */
    @ApiModelProperty("描述信息")
    private String description;

    /**
     * 0 停售 1 起售
     */
    @ApiModelProperty("停起售")
    private Integer status;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
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
}