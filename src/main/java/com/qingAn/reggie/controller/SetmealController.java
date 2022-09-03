package com.qingAn.reggie.controller;


import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.entity.Setmeal;
import com.qingAn.reggie.entity.SetmealDto;
import com.qingAn.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 作用：新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto, HttpSession session) {

        //1. 获取登陆者信息，补全信息
        Employee employee = (Employee) session.getAttribute("employee");
        setmealDto.setCreateUser(employee.getId());
        setmealDto.setUpdateUser(employee.getId());
        setmealService.save(setmealDto);
        return R.success("保存成功");
    }

    /**
     * 作用：展示套餐列表
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param name     菜品的名称
     * @return
     */
    @GetMapping("/page")
    public R<Page<SetmealDto>> page(Integer page, Integer pageSize, String name) {
        Page<SetmealDto> pageResult = setmealService.findByPage(page, pageSize, name);
        return R.success(pageResult);
    }

    /**
     * 作用：批量删除
     *
     * @param ids 要删除套餐的id
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        setmealService.deleteByIds(ids, employee.getId());
        return R.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public R<String> updateStatus(@RequestParam List<Long> ids, @PathVariable Integer status, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        setmealService.updateStatus(ids, status, employee.getId());
        return R.success("修改成功");
    }

    /**
     * 根据套餐的类别展示套餐
     * @param categoryId
     * @param status
     * @return
     */
    @GetMapping("list")
    public R<List<Setmeal>> list(Long categoryId, Integer status) {
        return R.success(setmealService.findByCategoryId(categoryId,status));
    }

}