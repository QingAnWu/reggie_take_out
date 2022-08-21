package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/login")
    public R<Employee> login(@RequestBody Employee employee , HttpSession session)   {
        R<Employee> r = employeeService.login(employee);

        if (r.getCode() == 1) {
            session.setAttribute("employee",r.getData());
        }
        return r;
    }

    @RequestMapping("/logout")
    public R<String> logout(HttpSession session){
        session.invalidate();
        return  R.success(null);
    }
}
