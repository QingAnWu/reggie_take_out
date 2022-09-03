package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {
    void saveBatch(@Param("orderDetailList") List<OrderDetail> orderDetailList);
}