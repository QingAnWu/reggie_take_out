package com.qingAn.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 卿安
 */

// @EnableWebMvc
// 开启Swagger2的自动配置

@EnableSwagger2
@Slf4j
// @Configuration //配置类
public class SwaggerConfig{
    @Bean
    public Docket addressBookApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安AddressBookController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.AddressBookController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket categoryApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安CategoryController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.CategoryController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket commonApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安CommonController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.CommonController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket dishApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安DishController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.DishController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket employeeApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安EmployeeController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.EmployeeController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket orderApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安OrderController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.OrderController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket setmealApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安SetmealController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.SetmealController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket shoppingCartApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安ShoppingCartController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.ShoppingCartController"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket userApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安UserController")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller.UserController"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置文档信息
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("卿安", "#", "571206595@qq.com");
        return new ApiInfo(
                "外卖Swagger3API文档",
                "文档描述信息",
                "v1.0",
                "#",
                contact,
                "Apach 2.0 许可",
                "许可链接",
                new ArrayList<>()
        );
    }
}

