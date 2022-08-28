package com.qingAn.reggie.service.impl;

import com.qingAn.reggie.entity.DishDto;
import com.qingAn.reggie.entity.DishFlavor;
import com.qingAn.reggie.mapper.DishFlavorMapper;
import com.qingAn.reggie.mapper.DishMapper;
import com.qingAn.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    }
}
