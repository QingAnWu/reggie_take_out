package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.DishDto;

/**
 * @author qingAn
 * @date 2022/08/28 20:33
 */
public interface DishService {

    /**
     * 菜品保存方法
     * @param dishDto
     */
    void save(DishDto dishDto);
}
