package com.qingAn.reggie.controller;

import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.Dish;
import com.qingAn.reggie.entity.DishDto;
import com.qingAn.reggie.entity.Employee;
import com.qingAn.reggie.entity.Page;
import com.qingAn.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/28 23:34
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;


    /**
     * 作用：保存菜品并且携带口味信息
     * @param dishDto  用于接收菜品与口味信息
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto, HttpSession session) {
        //1. 获取当前登陆者,补全创建者与更新者信息
        Employee employee = (Employee) session.getAttribute("employee");
        dishDto.setCreateUser(employee.getId());
        dishDto.setUpdateUser(employee.getId());
        //2. 保存菜品与口味
        dishService.save(dishDto);
        return R.success("保存菜品成功");
    }

    /**
     * 菜品信息分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page<DishDto>> findByPage(Integer page, Integer pageSize,String name) {
        return R.success(dishService.page(page, pageSize,name));
    }

    @GetMapping("/{id}")
    public R<Dish> get(@PathVariable Long id){
        Dish byId = dishService.findById(id);
        return R.success(byId);
    }

    /**
     * 作用：修改菜品
     * @param dishDto 页面传递过来的参数包含菜品与口味
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody  DishDto dishDto,HttpSession session) {
        //1. 获取当前登陆者,设置当前的修改人
        Employee employee = (Employee) session.getAttribute("employee");
        dishDto.setUpdateUser(employee.getId());
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    /**
     * 根据id关闭售卖状态
     * @param ids
     */
    @PostMapping("/status/0")
    public R<String> updateStatus0(String ids , HttpSession session){
        String[] list = ids.split(",");
        List<String> upList = new ArrayList<>(Arrays.asList(list));
        Employee employee = (Employee) session.getAttribute("employee");
        Dish dish = new Dish();
        dish.setUpdateUser(employee.getId());
        dishService.updateStatus0(upList,dish);
        return R.success("关闭售卖");
    }

    /**
     * 根据id开启售卖状态
     * @param ids
     */
    @PostMapping("/status/1")
    public R<String> updateStatus1(String ids , HttpSession session){
        String[] list = ids.split(",");
        List<String> upList = new ArrayList<>(Arrays.asList(list));
        Employee employee = (Employee) session.getAttribute("employee");
        Dish dish = new Dish();
        dish.setUpdateUser(employee.getId());
        dishService.updateStatus1(upList,dish);
        return R.success("启动售卖");
    }

    @DeleteMapping
    public R<String> deleteDish(String ids){
        String[] idList = ids.split(",");
        ArrayList<String> listId = new ArrayList<>(Arrays.asList(idList));
        dishService.deleteDish(listId);
        return R.success("成功删除");
    }

    /**
     * 方法作用： 根据菜品类别的id查找的菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Long categoryId,@RequestParam(defaultValue = "1") Integer status) {
        List<DishDto> dishList = dishService.findByCategoryId(categoryId,status);
        return R.success(dishList);
    }

}
