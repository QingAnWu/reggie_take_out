package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SetmealDishMapper {

    void saveBatch(@Param("setmealDishes") List<SetmealDish> setmealDishes);

    void deleteBySetmealIds(@Param("ids") List<Long> ids , @Param("opr") Long opr,@Param("updateTime") LocalDateTime updateTime);
}