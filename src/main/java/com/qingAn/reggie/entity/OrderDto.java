package com.qingAn.reggie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/09/28 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends Orders{
    private List<OrderDetail> orderDetails;
}
