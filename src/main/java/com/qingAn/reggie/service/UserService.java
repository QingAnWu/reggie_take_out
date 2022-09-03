package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.User;

/**
 * @author qingAn
 * @date 2022/09/03 15:06
 */
public interface UserService {

    /**
     * 登陆校验
     *
     * @param phone         用户输入的手机号
     * @param code          用户输入的验证码
     * @param codeInSession 系统的验证码
     * @return
     */
    User login(String phone, String code, String codeInSession);
}
