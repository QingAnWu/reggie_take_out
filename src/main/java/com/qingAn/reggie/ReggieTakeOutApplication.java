package com.qingAn.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * http://localhost:80/backend/page/login/login.html
 * @author qingAn
 */
@Slf4j
//扫描web原生注解，例如：@WebServlet,@WebFilter,@WebListener
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.qingAn.reggie.mapper")
@EnableTransactionManagement //开启对事物管理的支持
@EnableCaching
public class ReggieTakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeOutApplication.class, args);
        log.info("项目启动成功...");
    }
}