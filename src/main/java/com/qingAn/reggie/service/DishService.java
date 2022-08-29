package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.DishDto;
import com.qingAn.reggie.entity.Page;

/**
 * @author qingAn
 * @date 2022/08/28 20:33
 */
public interface DishService {

    /**
     * 作用：保存菜品并且携带口味信息
     *
     * @param dishDto 用于接收菜品与口味信息
     * @return
     */
    void save(DishDto dishDto);

    /**
     * 分页查询 通过name模糊查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Page<DishDto> page(int page, int pageSize, String name);

    /**
     * 作用：根据id查找菜品和菜品的口味
     * @param id  菜品的id
     * @return
     */
    DishDto findById(Long id);

    /**
     * 作用：修改菜品
     * @param dishDto 页面传递过来的参数包含菜品与口味
     * @return
     */
    void updateWithFlavor(DishDto dishDto);
}
