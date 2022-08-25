package com.qingAn.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Category;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.mapper.CategoryMapper;
import com.qingAn.reggie.mapper.DishMapper;
import com.qingAn.reggie.mapper.SetMealMapper;
import com.qingAn.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/25 9:33
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private DishMapper dishMapper;

    @Autowired(required = false)
    private SetMealMapper setMealMapper;

    @Override
    public R<String> saveCategory(Category category) {
        Category inquiry = categoryMapper.inquiry(category.getName());
        if (inquiry != null) {
            return R.error("类别已存在");
        }
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.saveCategory(category);
        return R.success("添加成功");
    }

    @Override
    public Page<Category> findByPage(Integer page, Integer pageSize) {
        //1. 设置当前页与页面大小
        PageHelper.startPage(page,pageSize);
        //2. 查询数据出来
        List<Category> categoryList = categoryMapper.findAll();

        //3. 把集合封装到PageInfo对象
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);

        //4. 把Pageinfo的数据封装到page对象中
        Page<Category> pageResult = new Page<>(pageInfo.getList(),pageInfo.getTotal(),pageSize,page);

        return pageResult;
    }

    /**
     * 根据id删除分类
     * @param id 分类id
     */
    @Override
    public void removeById(Long id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 作用： 根据id删除类别
     * @param id
     * @return
     */
    @Override
    public R<String> deleteById(Long id) {
        //1. 查询当前类别是否关联有菜品 ，返回的是菜品的总数
        long count = dishMapper.findDishCountByCategoryId(id);
        if(count>0){
            //异常类的作用：通知你当前程序出现何种类型问题，一类名有见名般异常知意的意思。 NullPointerException  WifiException
            return R.error("该类别关联了菜品，无法删除");
        }

        //2. 查询当前套餐是否关联有菜品
        count = setMealMapper.findSetMealByCategoryId(id);
        if(count>0){
            //异常类的作用：通知你当前程序出现何种类型问题，一类名有见名般异常知意的意思。 NullPointerException  WifiException
            return R.error("该类别关联了套餐，无法删除");
        }

        categoryMapper.deleteById(id);
        return R.success("分类信息删除成功");
    }

    /**
     * 作用：修改类别
     * @param category
     * @return
     */
    @Override
    public void updateById(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateById(category);
    }

}
