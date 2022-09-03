package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    @Insert("insert into orders values(#{id},#{number},#{status},#{userId},#{addressBookId},#{orderTime},#{checkoutTime},#{payMethod},#{amount},#{remark},#{phone},#{address},#{userName},#{consignee})")
    void save(Orders orders);
}