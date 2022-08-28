package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Param;
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
     * @param dishFlavors
     * @return
     */
    void saveBatch(@Param("dishFlavorList") List<DishFlavor> dishFlavors);
}
