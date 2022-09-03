package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Dish;
import com.qingAn.reggie.entity.DishDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/25 9:15
 */
@Repository
public interface DishMapper {
    /**
     * mapper的方法添加@param注解在什么情况需要添加：
     * 1. mapper方法是有多个参数的时候（两个以及两个以上的参数需要添加）
     * 2. mapper方法只有一个参数，并且这个参数是简单类型，而且你还使用了动态sql也是需要使用@Param
     * 3. mapper方法只有一个参数，参数是引用类型，也使用了动态sql，不需要添加。
     * 4. 遍历map或者list集合,数组的时候也是需要添加的。
     *
     * @param categoryId
     * @return
     */
    @Select("SELECT COUNT(0) FROM dish WHERE category_id=#{categoryId}")
    long findDishCountByCategoryId(Long categoryId);

    /**
     * 作用：插入菜品，而且需要把主键设置会给实体类
     * Options 通知mybatis插入数据之后，把id列的值设置给实体类的id属性
     *
     * @param dishDto
     */
    @Insert("insert into  dish values(null,#{name},#{categoryId},#{price},#{code},#{image},#{description},#{status},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser},0)")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void save(DishDto dishDto);

    /**
     * 分页查询
     *
     * @param name
     * @return
     */
    List<DishDto> findByPage(String name);

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    @Select("select * from dish where id=#{id}")
    Dish findById(Long id);

    /**
     * 根据id修改菜品
     *
     * @param dishDto
     */
    void updateById(DishDto dishDto);

    /**
     * 根据id关闭售卖状态
     *
     * @param ids
     */
    void updateStatus0(@Param("ids") List ids, @Param("dish") Dish dish);

    /**
     * 根据id开启售卖状态
     *
     * @param ids
     */
    void updateStatus1(@Param("ids") List ids, @Param("dish") Dish dish);

    /**
     * 根据批量删除菜品
     *
     * @param ids
     */
    void deleteDish(@Param("ids") List ids);

    /**
     * 方法作用： 根据菜品类别的id查找的菜品
     *
     * @param categoryId
     */
    @Select("select * from dish where category_id=#{categoryId} and status=#{status} ")
    List<Dish> findByCategoryId(@Param("categoryId") Long categoryId, @Param("status") Integer status);
}
