package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper {

    @Insert("insert into orders values(#{id},#{number},#{status},#{userId},#{addressBookId},#{orderTime},#{checkoutTime},#{payMethod},#{amount},#{remark},#{phone},#{address},#{userName},#{consignee})")
    void save(Orders orders);

    List<Orders> pagingQuery(@Param("number") String number , @Param("beginTime") Date beginTime , @Param("endTime") Date endTime);

    List<Orders> userPage(Long id);
}
