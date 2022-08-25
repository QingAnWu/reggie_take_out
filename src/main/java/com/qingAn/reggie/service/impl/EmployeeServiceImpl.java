package com.qingAn.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.exception.BusinessException;
import com.qingAn.reggie.mapper.EmployeeMapper;
import com.qingAn.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        Employee doEmployee = employeeMapper.login(employee);
        if (doEmployee != null) {
            throw new BusinessException("用户名已存在");
        }
        //设置默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置默认状态
        employee.setStatus(1);
        //设置默认创建与跟新时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //2. 把数据交给mapper插入
        employeeMapper.save(employee);
    }

    @Override
    public Page<Employee> findByPage(Integer page, Integer pageSize, String name) {
        //1. 设置当前页与页面大小
        PageHelper.startPage(page, pageSize);

        //2. 查询数据
        List<Employee> employeeList = employeeMapper.findByName(name);

        //3. 创建PageINfo对象，把list集合传入
        PageInfo<Employee> pageInfo = new PageInfo(employeeList);

        //4. 把pageinfo的信息转移到Page对象
        Page<Employee> pageResult = new Page<>(
                pageInfo.getList(),
                pageInfo.getTotal(),
                pageInfo.getPageSize(),
                pageInfo.getPageNum());

        return pageResult;
    }

    @Override
    public void update(Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.update(employee);
    }

    @Override
    public R<Employee> findById(Long id) {
        Employee employee = employeeMapper.findById(id);
        return R.success(employee);
    }
}
