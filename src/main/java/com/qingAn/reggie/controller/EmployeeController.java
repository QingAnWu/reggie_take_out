package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author qingAn
 * @date 2022/08/21 9:26
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录 http://localhost:8080/employee/login
     *
     * @return r
     */
    @RequestMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpSession session) {
        R<Employee> r = employeeService.login(employee);

        if (r.getCode() == 1) {
            session.setAttribute("employee", r.getData());
        }
        return r;
    }

    /**
     * 注销
     *
     * @return R.success(null)
     */
    @RequestMapping("/logout")
    public R<String> logout(HttpSession session) {
        session.invalidate();
        return R.success(null);
    }

    /**
     * 新增用户方法
     *
     * @param employee 用于封装json的数据
     * @param session  会话域
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpSession session) {
        //1. 获取当前登陆的用户,补全员工的创建者与修改者
        Long empId = (Long) session.getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        //2. 把数据交给service
        employeeService.save(employee);
        return R.success("保存成功");
    }
}
