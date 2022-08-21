package com.qingAn.reggie.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工
 * @author qingAn
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 登录的时候使用用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 驼峰命名法 ---> 映射的字段名为 id_number
     */
    private String idNumber;
    /**
     * 用户的状态  0禁用 1启用
     */
    private Integer status;
    /**
     * 创建用户的时间
     */
    private LocalDateTime createTime;
    /**
     * 修改用户的时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建用户的人
     */
    private Long createUser;
    /**
     * 修改用户的人
     */
    private Long updateUser;
}