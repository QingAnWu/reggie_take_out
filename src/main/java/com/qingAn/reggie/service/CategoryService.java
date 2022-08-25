package com.qingAn.reggie.service;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Category;
import com.qingAn.reggie.entity.Page;

/**
 * @author qingAn
 * @date 2022/08/25 9:32
 */
public interface CategoryService {

    /**
     * 保存类别
     *
     * @param category 类别
     */
    R<String> saveCategory(Category category);

    /**
     * 作用： 类别分页管理
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @return
     */
    Page<Category> findByPage(Integer page, Integer pageSize);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void removeById(Long id);


    /**
     * 作用： 根据id删除类别
     *
     * @param id
     * @return
     */
    R<String> deleteById(Long id);

    /**
     * 作用：修改类别
     *
     * @param category
     * @return
     */
    void updateById(Category category);
}
