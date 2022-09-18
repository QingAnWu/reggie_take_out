package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Category;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/25 9:13
 */
@Slf4j
@RestController
@Api(value = "/category",tags = "类别分类")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category
     * @param session
     * @return 添加类别成功
     */

    @ApiOperation("添加类别")
    @PostMapping
    public R<String> saveCategory(@RequestBody Category category, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        category.setUpdateUser(employee.getId());
        category.setCreateUser(employee.getId());
        return categoryService.saveCategory(category);
    }

    /**
     * 作用： 类别分页管理
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @return
     */
    @ApiOperation("类别分页管理")
    @GetMapping("/page")
    public R<Page<Category>> findByPage(Integer page, Integer pageSize) {
        Page<Category> byPage = categoryService.findByPage(page, pageSize);
        return R.success(byPage);
    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除分类")
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        return categoryService.deleteById(id);
    }

    /**
     * 作用：修改类别
     *
     * @param category
     * @return
     */
    @ApiOperation("修改类别")
    @PutMapping
    public R<String> update(@RequestBody Category category, HttpSession session) {
        //1. 获取当前登陆者
        Employee employee = (Employee) session.getAttribute("employee");
        //2. 修改修改者的信息
        category.setUpdateUser(employee.getId());
        categoryService.updateById(category);

        return R.success("修改成功");
    }

    /**
     * 作用：根据Type查询类别集合
     *
     * @param type 类别的type
     * @return
     */
    @ApiOperation("根据Type查询类别集合")
    @GetMapping("list")
    public R<List<Category>> list(Integer type) {
        List<Category> categoryList = categoryService.findAllByType(type);
        return R.success(categoryList);
    }

}