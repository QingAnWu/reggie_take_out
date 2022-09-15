package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类别分类
 * @author qingAn
 */
@Data
@Validated
@ApiModel("类别分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别id
     */
    @ApiModelProperty("类别id")
    private Long id;

    /**
     * 类型 1 菜品分类 2 套餐分类
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;

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