package com.qingAn.reggie.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author 卿安
 */

// @EnableWebMvc
// @EnableSwagger2 开启Swagger2的自动配置


@Slf4j
@Configuration
@Import(WebMvcConfig.class)
public class SwaggerConfig{


    @Bean
    public Docket userApi(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        log.info(String.valueOf(flag));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("卿安")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingAn.reggie.controller"))
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

