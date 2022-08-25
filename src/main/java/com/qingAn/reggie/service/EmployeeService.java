package com.qingAn.reggie.service;


import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;

/**
 * @author qingAn
 * @date 2022/08/21 0:16
 */
public interface EmployeeService {

    /**
     * 登录的方法
     *
     * @param employee 员工数据
     * @return R 封装返回结构
     */
    R<Employee> login(Employee employee);

    /**
     * 保存员工
     *
     * @param employee 员工数据
     */
    void save(Employee employee);

    /**
     * 作用： 员工列表分页
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param name     用户名
     * @return Page 返回封装的数据
     */
    Page<Employee> findByPage(Integer page, Integer pageSize, String name);

    void update(Employee employee);

    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    R<Employee> findById(Long id);
}
