package com.qingAn.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qingAn
 * @date 2022/08/21 19:52
 */

@Slf4j
// @WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // 该类的作用主要用于匹配url地址
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 定义一批可以直接放行的资源地址
     */
    String[] urls = {
            "/employee/login",
            "/backend/**",
            "/front/**",
            "/user/sendMsg",
            "/user/login"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父接口servletRequest 转换为HttpServletRequest 强制类型转换是为了使用子类的特有方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取本次请求uri   /backend/index.html
        String requestURI = request.getRequestURI();

        // 判断本次请求的路径是否需要处理
        for (String url : urls) {
            if (antPathMatcher.match(url, requestURI)) {
                //放行
                filterChain.doFilter(request, response);
                return;
            }
        }

        // 判断登录状态，如果已登录，则直接放行
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            log.info("用户已登录，用户id为：{}", ((User) request.getSession().getAttribute("user")).getId());
            filterChain.doFilter(request, response);
            return;
        }

        // 判断员工登入状态
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee != null) {
            log.info("用户已登录，用户id为：{}", ((Employee) request.getSession().getAttribute("employee")).getId());
            //登录成功 放行
            filterChain.doFilter(request, response);
            return;
        }

        R<Object> r = R.error("NOTLOGIN");
        //转换为json
        String jsonStr = JSON.toJSONString(r);
        //手动设置响应数据类型
        servletResponse.setContentType("application/json;charset=utf-8");
        servletResponse.getWriter().print(jsonStr);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
