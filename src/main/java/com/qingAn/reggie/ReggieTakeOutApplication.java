package com.qingAn.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * http://localhost:8080/backend/page/login/login.html
 * @author qingAn
 */
@Slf4j
//扫描web原生注解，例如：@WebServlet,@WebFilter,@WebListener
@ServletComponentScan
@MapperScan("com.qingAn.reggie.mapper")
@SpringBootApplication
public class ReggieTakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeOutApplication.class, args);
        log.info("项目启动成功...");

    }
}