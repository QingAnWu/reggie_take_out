package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工
 *
 * @author qingAn
 */
@Data
@ApiModel("员工信息")
public class Employee implements Serializable {
    private static final long serialVersionUID = -4807652658640141478L;
    /**
     * 用户id
     *    // @JsonFormat(shape = JsonFormat.Shape.STRING)
     * @JsonFormat 用来表示json序列化的一种格式或者类型，shape表示序列化后的一种类型
     */
    @ApiModelProperty("用户id")
    private Long id;
    /**
     * 登录的时候使用用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 驼峰命名法 ---> 映射的字段名为 id_number
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    private String idNumber;
    /**
     * 用户的状态  0禁用 1启用
     */
    @ApiModelProperty("用户的状态")
    private Integer status;
    /**
     * 创建用户的时间
     */
    @ApiModelProperty("创建用户的时间")
    private LocalDateTime createTime;
    /**
     * 修改用户的时间
     */
    @ApiModelProperty("修改用户的时间")
    private LocalDateTime updateTime;
    /**
     * 创建用户的人
     */
    @ApiModelProperty("创建用户的人")
    private Long createUser;
    /**
     * 修改用户的人
     */
    @ApiModelProperty("修改用户的人")
    private Long updateUser;
}