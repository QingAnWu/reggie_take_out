package com.qingAn.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.*;
import com.qingAn.reggie.exception.BusinessException;
import com.qingAn.reggie.mapper.CategoryMapper;
import com.qingAn.reggie.mapper.SetMealMapper;
import com.qingAn.reggie.mapper.SetmealDishMapper;
import com.qingAn.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 作用：新增套餐
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetmealDto setmealDto) {
        //补全setmeal的信息，比如:、修改、创建时间、
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());
        //调用保存的方法 把套餐的id设置给实体类
        setMealMapper.save(setmealDto);


        //得到套餐的菜品，给所有的菜品补全信息(setmeal_id 、修改、创建时间、排序，)
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(setmealDish -> {
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setCreateUser(setmealDto.getCreateUser());
            setmealDish.setUpdateUser(setmealDto.getUpdateUser());
            setmealDish.setCreateTime(LocalDateTime.now());
            setmealDish.setUpdateTime(LocalDateTime.now());
            setmealDish.setSort(0);
            return setmealDish;
        }).collect(Collectors.toList());

        //批量插入套餐菜品表
        setmealDishMapper.saveBatch(setmealDishes);

    }

    /**
     * 作用：展示套餐列表
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param name     菜品的名称
     * @return
     */
    @Override
    public Page<SetmealDto> findByPage(Integer page, Integer pageSize, String name) {
        //1. 设置当前页与页面大小
        PageHelper.startPage(page, pageSize);
        //2. 根据name查询数据，得到List集合  List<Setmeal>  select * from setmeal
        List<Setmeal> setmealList = setMealMapper.findByName(name);

        //3. 创建PageInfo对象，只有创建PageInfo对象了，上面的sql语句才会真正的去执行。
        PageInfo<Setmeal> pageInfo = new PageInfo<>(setmealList);
        //4. 遍历List<Setmeal> ,把Setmeal转换为SetmealDto
        List<SetmealDto> setmealDtoList = new ArrayList<>();

        for (Setmeal setmeal : pageInfo.getList()) {
            //每一个setmeal对应一个setmealDto
            SetmealDto setmealDto = new SetmealDto();
            //属性拷贝
            BeanUtils.copyProperties(setmeal, setmealDto);
            //查找套餐类别
            Category category = categoryMapper.findById(setmeal.getCategoryId());
            setmealDto.setCategoryName(category.getName());
            setmealDtoList.add(setmealDto);
        }
        //5. 创建page对象
        Page<SetmealDto> pageResult = new Page<>(setmealDtoList, pageInfo.getTotal(), page, pageSize);
        return pageResult;
    }

    /**
     * 作用：批量删除
     *
     * @param ids 要删除套餐的id
     * @return
     */
    @Override
    public void deleteByIds(List<Long> ids, Long opr) {
        //1.检查要删除的套餐里面有没有在售,如果存在着在售套餐，直接抛出异常，不能删除
        Long count = setMealMapper.queryDishWithStatus(ids);
        if (count > 0) {
            throw new BusinessException("存在套餐是在售状态，无法删除");
        }

        //2. 如果要删除套餐都是停售，那么就可以直接删除套餐
        setMealMapper.deleteByIds(ids, opr, LocalDateTime.now());

        //3. 删除套餐对应的套餐菜品
        setmealDishMapper.deleteBySetmealIds(ids, opr, LocalDateTime.now());
    }

    @Override
    public void updateStatus(List<Long> ids, Integer status, Long opr) {
        setMealMapper.updateStatus(ids, status, opr, LocalDateTime.now());
    }

    /**
     * 根据套餐的类别展示套餐
     * @param categoryId
     * @param status
     * @return
     */
    @Override
    public List<Setmeal> findByCategoryId(Long categoryId, Integer status) {
        return setMealMapper.findByCategoryId(categoryId,status);
    }

    @Override
    public R<SetmealDto> queryId(long id) {
        List<SetmealDish> setmealDishes = setmealDishMapper.queryId(id);
        Setmeal setmeals = setMealMapper.queryId(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeals,setmealDto);
        setmealDto.setSetmealDishes(setmealDishes);
        return R.success(setmealDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetmealDto setmealDto) {
        setmealDto.setUpdateTime(LocalDateTime.now());

        try {
            setMealMapper.update(setmealDto);
        } catch (Exception e) {
            throw new BusinessException("数据有误",e);
        }

        setmealDishMapper.deleteById(setmealDto.getId());

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.forEach(
                setmealDish -> {
                    setmealDish.setSort(0);
                    setmealDish.setSetmealId(setmealDto.getId());
                    setmealDish.setUpdateUser(setmealDto.getUpdateUser());
                    setmealDish.setCreateUser(setmealDto.getUpdateUser());
                    setmealDish.setUpdateTime(LocalDateTime.now());
                    setmealDish.setCreateTime(LocalDateTime.now());
                }
        );

        setmealDishMapper.saveBatch(setmealDishes);

    }
}
