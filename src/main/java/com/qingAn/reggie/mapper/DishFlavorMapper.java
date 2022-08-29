package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/28 20:22
 */
@Repository
public interface DishFlavorMapper {
    /**
     * 批量插入多条口味记录
     *
     * @param dishFlavors
     * @return
     */
    void saveBatch(@Param("dishFlavorList") List<DishFlavor> dishFlavors);

    /**
     * 查询多条口味记录
     *
     * @param dishFlavors
     * @return
     */
    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> findByDishId(Long id);

    /**
     * 删除口味记录
     *
     * @param id
     */
    @Delete("delete from dish_flavor where dish_id=#{dishId}")
    void deleteByDishId(Long id);
}
