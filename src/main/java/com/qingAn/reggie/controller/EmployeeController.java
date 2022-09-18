package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author qingAn
 * @date 2022/08/21 9:26
 */
@RestController
@Api(value = "/employee" ,tags = "员工控制器")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     *
     * @return r
     */
    @ApiOperation("登录")
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
    @ApiOperation("注销")
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
     * @return 保存成功
     */
    @ApiOperation("新增用户方法")
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpSession session) {
        //1. 获取当前登陆的用户,补全员工的创建者与修改者
        Employee loginEmployee = (Employee) session.getAttribute("employee");
        employee.setCreateUser(loginEmployee.getId());
        employee.setUpdateUser(loginEmployee.getId());
        //2. 把数据交给service
        employeeService.save(employee);
        return R.success("保存成功");
    }

    /**
     * 作用： 员工列表分页
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param name     用户名
     * @return pageResult
     */
    @ApiOperation("员工列表分页")
    @GetMapping("/page")
    public R<Page<Employee>> page(Integer page, Integer pageSize, String name) {
        Page<Employee> pageResult = employeeService.findByPage(page, pageSize, name);
        return R.success(pageResult);
    }

    /**
     * 作用： 更新员工信息
     * @param employee  封装所有提交过来的员工信息
     * @param session   会话域对象
     * @return 修改成功
     */
    @ApiOperation("更新员工信息")
    @PutMapping
    public R<String> update(@RequestBody  Employee employee, HttpSession session ){
        //1. 获取当前登陆用户，修改更新者信息
        Employee loginEmployee = (Employee) session.getAttribute("employee");
        employee.setUpdateUser(loginEmployee.getId());
        //2. 把信息交给service
        employeeService.update(employee);
        return R.success("修改成功");
    }

    /**
     *根据id查找员工
     * @return
     */
    @ApiOperation("根据id查找员工")
    @GetMapping("/{id}")
    public R<Employee> findById(@PathVariable Long id){
        return employeeService.findById(id);
    }
}
