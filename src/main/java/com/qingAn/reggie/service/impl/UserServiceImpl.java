package com.qingAn.reggie.service.impl;

import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.mapper.UserMapper;
import com.qingAn.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qingAn
 * @date 2022/09/03 15:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆校验
     *
     * @param phone         用户输入的手机号
     * @param code          用户输入的验证码
     * @param codeInSession 系统的验证码
     * @return
     */
    @Override
    public User login(String phone, String code, String codeInSession) {
        User dbUser = null;
        if (codeInSession != null && codeInSession.equalsIgnoreCase(code)) {
            dbUser = userMapper.findByPhone(phone);
            if (dbUser == null) {
                dbUser = new User();
                dbUser.setPhone(phone);
                dbUser.setStatus(1);
                userMapper.addUser(dbUser);
                dbUser = userMapper.findByPhone(phone);
            }
        }
        return dbUser;
    }
}
