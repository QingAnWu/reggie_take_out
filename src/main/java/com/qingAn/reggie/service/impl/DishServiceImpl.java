package com.qingAn.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingAn.reggie.entity.*;
import com.qingAn.reggie.mapper.CategoryMapper;
import com.qingAn.reggie.mapper.DishFlavorMapper;
import com.qingAn.reggie.mapper.DishMapper;
import com.qingAn.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author qingAn
 * @date 2022/08/28 20:34
 */
@Service
public class DishServiceImpl implements DishService {

    /**
     * 菜品mapper
     */
    @Autowired
    private DishMapper dishMapper;

    /**
     * 口味Mapper
     */
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 菜品保存方法
     *
     * @param dishDto 新增的菜品数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DishDto dishDto) {
        dishDto.setCreateTime(LocalDateTime.now());
        dishDto.setUpdateTime(LocalDateTime.now());
        dishDto.setSort(0);
        dishDto.setStatus(1);
        //调用口味记录列表完善数据，要求获取到添加后菜单主键
        dishMapper.save(dishDto);

        //3. 补全口味的 dish_id 、 创建时间、修改时间、创建人、修改人、
        //获取所有的口味信息
        List<DishFlavor> flavors = dishDto.getFlavors();
        //遍历所有口味信息，补全dish_id 、 创建时间、修改时间、创建人、修改人、
      /* 传统用法
      for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDto.getId());
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setCreateUser(dishDto.getCreateUser());
            flavor.setUpdateUser(dishDto.getUpdateUser());
        }*/
        //使用jdk8的新特性，stream, map映射
        List<DishFlavor> dishFlavorList = flavors.stream().map((flavor -> {
            //对每一个元素进行加工
            flavor.setDishId(dishDto.getId());
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setCreateUser(dishDto.getCreateUser());
            flavor.setUpdateUser(dishDto.getUpdateUser());
            return flavor;
        })).collect(Collectors.toList());

        //4. 批量保存口味信息
        dishFlavorMapper.saveBatch(dishFlavorList);
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
    }

    /**
     * 菜品信息分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<DishDto> page(int page, int pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<DishDto> byPage = dishMapper.findByPage(name);
        PageInfo<DishDto> dishDtoPageInfo = new PageInfo<>(byPage);

        Page<DishDto> dishDtoPage = new Page<>();
        dishDtoPage.setTotal(dishDtoPageInfo.getTotal());
        dishDtoPage.setRecords(dishDtoPageInfo.getList());
        return dishDtoPage;
    }

    /**
     * 作用：根据id查找菜品和菜品的口味
     *
     * @param id 菜品的id
     * @return
     */
    @Override
    public DishDto findById(Long id) {
        Dish byId = dishMapper.findById(id);
        List<DishFlavor> byDishId = dishFlavorMapper.findByDishId(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(byId, dishDto);
        dishDto.setFlavors(byDishId);
        return dishDto;
    }

    /**
     * 作用：修改菜品
     *
     * @param dishDto 页面传递过来的参数包含菜品与口味
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWithFlavor(DishDto dishDto) {
        //1. 补全修改时间
        dishDto.setUpdateTime(LocalDateTime.now());
        //2 修改菜品
        dishMapper.updateById(dishDto);

        //3. 删除该菜品的所有口味信息
        dishFlavorMapper.deleteByDishId(dishDto.getId());

        //4. 补全口味信息，然后重新插入
        List<DishFlavor> flavors = dishDto.getFlavors();
        List<DishFlavor> dishFlavorList = flavors.stream().map((flavor -> {
            //对每一个元素进行加工
            flavor.setDishId(dishDto.getId());
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setCreateUser(dishDto.getCreateUser());
            flavor.setUpdateUser(dishDto.getUpdateUser());
            return flavor;
        })).collect(Collectors.toList());

        //4. 批量保存口味信息
        dishFlavorMapper.saveBatch(dishFlavorList);

        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

    }

    /**
     * 根据id关闭售卖状态
     *
     * @param ids
     */
    @Override
    public void updateStatus0(List ids, Dish dish) {
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.updateStatus0(ids, dish);
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
    }

    /**
     * 根据id开启售卖状态
     *
     * @param ids
     */
    @Override
    public void updateStatus1(List ids, Dish dish) {
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.updateStatus1(ids, dish);
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
    }

    /**
     * 根据批量删除菜品
     *
     * @param ids
     */
    @Override
    public void deleteDish(List ids) {
        dishMapper.deleteDish(ids);
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
    }

    /**
     * 方法作用： 根据菜品类别的id查找的菜品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<DishDto> findByCategoryId(Long categoryId, Integer status) {

        List<DishDto> dishDtoList = null;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //1. 先查询redis，查看redis是否存在该类别的菜品
        String key = "dish_"+categoryId+"_"+status;
        dishDtoList = (List<DishDto>)valueOperations.get(key);

        if (dishDtoList==null){
            List<Dish> dishList = dishMapper.findByCategoryId(categoryId, status);
            //遍历所有的dish，把dish转换为dto
            dishDtoList = dishList.stream().map(dish -> {
                DishDto dishDto = new DishDto();
                //属性拷贝
                BeanUtils.copyProperties(dish, dishDto);
                //查看该菜品的口味信息
                List<DishFlavor> dishFlavorList = dishFlavorMapper.findByDishId(dish.getId());
                dishDto.setFlavors(dishFlavorList);
                //类别信息
                Category category = categoryMapper.findById(dish.getCategoryId());
                dishDto.setCategoryName(category.getName());
                return dishDto;
            }).collect(Collectors.toList());

            //如果菜品是从数据库中查询出来的，那么必须要添加到缓存中 ，缓存保留一天
            valueOperations.set(key,dishDtoList,60*24, TimeUnit.MINUTES);
        }
        return dishDtoList;
    }
}
