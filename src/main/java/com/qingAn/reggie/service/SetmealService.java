package com.qingAn.reggie.service;

import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.entity.SetmealDto;

import java.util.List;

public interface SetmealService {

    /**
     * 作用：新增套餐
     *
     * @param setmealDto
     */
    void save(SetmealDto setmealDto);

    /**
     * 作用：展示套餐列表
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param name     菜品的名称
     */
    Page<SetmealDto> findByPage(Integer page, Integer pageSize, String name);

    /**
     * 作用：批量删除
     *
     * @param ids 要删除套餐的id
     * @return
     */
    void deleteByIds(List<Long> ids, Long opr);

    /**
     * 通过id批量启售停售
     * @param ids
     */
    void updateStatus(List<Long> ids ,Integer status, Long opr);
}