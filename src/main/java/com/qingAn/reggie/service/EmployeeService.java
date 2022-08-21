package com.qingAn.reggie.service;


import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;

/**
 * @author qingAn
 * @date 2022/08/21 0:16
 */
public interface EmployeeService {

    /**
     * 登录的方法
     * @param employee 员工数据
     * @return R 封装返回结构
     */
    R<Employee> login(Employee employee);
}
