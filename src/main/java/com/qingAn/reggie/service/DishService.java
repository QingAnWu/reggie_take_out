package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.Dish;
import com.qingAn.reggie.entity.DishDto;
import com.qingAn.reggie.entity.Page;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/28 20:33
 */
public interface DishService {

    /**
     * 作用：保存菜品并且携带口味信息
     *
     * @param dishDto 用于接收菜品与口味信息
     */
    void save(DishDto dishDto);

    /**
     * 分页查询 通过name模糊查询
     */
    Page<DishDto> page(int page, int pageSize, String name);

    /**
     * 作用：根据id查找菜品和菜品的口味
     */
    DishDto findById(Long id);

    /**
     * 作用：修改菜品
     * @param dishDto 页面传递过来的参数包含菜品与口味
     */
    void updateWithFlavor(DishDto dishDto);

    /**
     * 根据id批量关闭售卖状态
     */
    void updateStatus0(List ids , Dish dish);

    /**
     * 根据id批量开启售卖状态
     */
    void updateStatus1(List ids ,Dish dish);

    /**
     * 根据批量删除菜品
     */
    void deleteDish(List ids);

    /**
     * 方法作用： 根据菜品类别的id查找的菜品
     */
    List<Dish> findByCategoryId(Long categoryId);
}
