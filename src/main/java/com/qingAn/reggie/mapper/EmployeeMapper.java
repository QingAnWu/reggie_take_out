package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/21 0:09
 */
@Repository
public interface EmployeeMapper {

    /**
     * 根据账户名查找一个员工信息
     *
     * @param employee 员工数据
     * @return Employee 返回结果
     */
    @Select("select * from employee where username=#{username}")
    Employee login(Employee employee);

    /**
     * 插入数据
     * @param employee 员工数据
     */
    @Insert("insert into employee values(null,#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void save(Employee employee);

    /**
     * 模糊查询分页
     * @param name %name% 员工姓名
     * @return List<Employee> 员工数据
     */
    List<Employee> findByName(@Param("name") String name);
}
