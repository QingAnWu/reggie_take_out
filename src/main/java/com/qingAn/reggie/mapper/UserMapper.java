package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author qingAn
 * @date 2022/09/03 14:40
 */
@Repository
public interface UserMapper {

    /**
     * 通过电话查询用户信息
     *
     * @return
     */
    User findByPhone(String phone);

    /**
     * 添加用户信息
     */
    void addUser(User user);

    @Select("select * from user where id=#{userId}")
    User findById(Long userId);
}
