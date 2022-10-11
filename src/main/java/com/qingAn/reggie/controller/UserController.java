package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.UserService;
import com.qingAn.reggie.utils.RedisUtil;
import com.qingAn.reggie.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
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
@Api(value = "/user",tags = "用户控制器")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 作用： 发送短信
     *
     * @param user
     * @return
     */
    @ApiOperation("发送短信")
    @PostMapping("/sendMsg")
    public R<String> sendMg(@RequestBody User user) {
        String code = ValidateCodeUtils.generateValidateCode4String(4);
        log.info("本次验证码：" + code);
        //发送手机验证码

        //将生成的验证码存储在redis中,并设置有效时间为一分钟
        redisUtil.set(user.getPhone(), code,60);

        return R.success("短信发送成功");
    }

    /**
     * 登陆校验
     */
    @ApiOperation("登陆校验")
    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String, String> param , HttpSession session) {
        //取出参数
        String phone = param.get("phone");
        //用户输入的验证码
        String code = param.get("code");

        String codeInRedis =(String) redisUtil.get(phone);

        User user = userService.login(phone, code, codeInRedis);
        if (user == null) {
            return R.error("登录失败");
        }else {
            redisUtil.del(phone);
            session.setAttribute("user", user);
        }
        return R.success(user);
    }

    @ApiOperation("退出登录")
    @PostMapping("/loginout")
    public R<String> logout(HttpSession session){
        session.invalidate();
        return R.success("成功退出");
    }
}
