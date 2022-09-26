package com.qingAn.reggie;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.controller.CommonController;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.mapper.EmployeeMapper;
import com.qingAn.reggie.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

@Slf4j
@SpringBootTest
class ReggieTakeOutApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired(required = false)
    private JavaMailSenderImpl mailSender;

    @Test
    void TestcontextLoads() {
        //1. 设置当前页与页面大小 注意这两行代码一定要紧挨着
        PageHelper.startPage(1,2);
        //2. 查找数据
        List<Employee> employeeList  = employeeMapper.findByName("卿");

        //3. 得到分页对象
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        System.out.println("总记录数："+ pageInfo.getTotal());
        System.out.println("总页数："+ pageInfo.getPages());
        System.out.println("当前页："+ pageInfo.getPageNum());
        System.out.println("页面大小："+ pageInfo.getPageSize());
    }

    @Value("${takeout.path}")
    private String baseDir;

    @Test
    void Test01(){
        System.out.println(CommonController.class.getResource("/"));
        String path = CommonController.class.getResource("/").getPath();
        System.out.println(path+baseDir);
    }

    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("ikun");
        message.setText("练习两年半的实习生，鸡你太美");
        // message.setTo("571206595@qq.com");
        message.setFrom("wuqingan3306@163.com");
        mailSender.send(message);
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest(){
        redisUtil.set("name","hello");
        System.out.println(redisUtil.get("name"));
    }

}
