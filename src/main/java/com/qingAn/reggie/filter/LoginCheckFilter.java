package com.qingAn.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author qingAn
 * @date 2022/08/21 19:52
 */

@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // 该类的作用主要用于匹配url地址
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父接口servletRequest 转换为HttpServletRequest 强制类型转换是为了使用子类的特有方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取本次请求uri   /backend/index.html
        String requestURI = request.getRequestURI();

        //2 定义一批可以直接放行的资源地址
        String[] urls = {
                "/employee/login",
                "/backend/**",
                "/front/**"
        };

        //3. 判断本次请求的路径是否需要处理
        for (String url : urls) {
            if (antPathMatcher.match(url, requestURI)) {
                //放行
                filterChain.doFilter(request, response);
                return;
            }
        }

        //4. 判断登入状态
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee == null) {
            R<Object> r = R.error("NOTLOGIN");
            //转换为json
            String jsonStr = JSON.toJSONString(r);
            //手动设置响应数据类型
            servletResponse.setContentType("application/json;charset=utf-8");
            servletResponse.getWriter().print(jsonStr);
        } else {
            //登录成功 放行
            filterChain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
