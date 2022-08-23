package com.qingAn.reggie.service.impl;

import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.mapper.EmployeeMapper;
import com.qingAn.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qingAn.reggie.common.R;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * @author qingAn
 * @date 2022/08/21 0:20
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public R<Employee> login(Employee employee) {
        Employee loginEmployee = employeeMapper.login(employee);
        if (loginEmployee == null) {
            return R.error("用户或密码错误");
        }
        String userMd5 = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        if (!loginEmployee.getPassword().equals(userMd5)) {
            return R.error("用户名或密码错误");
        }
        if (loginEmployee.getStatus() == 0) {
            return R.error("已被禁用，请联系管理员");
        }
        return R.success(loginEmployee);
    }

    @Override
    public void save(Employee employee) {
        //1. 补全密码，状态，创建、修改时间
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setStatus(1);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //2. 把数据交给mapper插入
        employeeMapper.save(employee);
    }
}
