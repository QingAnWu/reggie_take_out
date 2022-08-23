package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
     * @return employee 返回结果
     */
    @Select("select * from employee where username=#{username}")
    Employee login(Employee employee);

    @Insert("insert into employee values(null,#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void save(Employee employee);
}
