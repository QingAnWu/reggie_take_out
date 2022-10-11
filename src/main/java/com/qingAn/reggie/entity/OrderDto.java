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
    private static final long serialVersionUID = 6734026660829624533L;
    private List<OrderDetail> orderDetails;
}
