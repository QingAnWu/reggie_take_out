package com.qingAn.reggie.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author qingAn
 * @date 2022/08/25 9:15
 */
@Repository
public interface SetMealMapper {


    //根据类别的id查找套餐总数
    @Select("select count(0) from setmeal where category_id=#{id}")
    long findSetMealByCategoryId(Long id);
}
