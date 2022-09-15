package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.UserService;
import com.qingAn.reggie.utils.ValidateCodeUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author qingAn
 * @date 2022/09/02 20:55
 */
@Slf4j
@ApiOperation("用户控制器")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 作用： 发送短信
     *
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMg(@RequestBody User user, HttpSession session) {
        String code = ValidateCodeUtils.generateValidateCode4String(4);
        log.info("本次验证码：" + code);
        //发送手机验证码

        //将生成的验证码存储在session中
        session.setAttribute(user.getPhone(), code);

        return R.success("短信发送成功");
    }

    /**
     * 登陆校验
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String, String> param, HttpSession session) {
        //取出参数
        String phone = param.get("phone");
        //用户输入的验证码
        String code = param.get("code");
        String codeInSession = (String) session.getAttribute(phone);
        User user = userService.login(phone, code, codeInSession);
        if (user == null) {
            return R.error("登录失败");
        }
        session.setAttribute("user", user);
        return R.success(user);
    }
}
