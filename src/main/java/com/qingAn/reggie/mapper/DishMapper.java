package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.DishDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
     * @param dishDto
     */
    @Insert("insert into  dish values(null,#{name},#{categoryId},#{price},#{code},#{image},#{description},#{status},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser},0)")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void save(DishDto dishDto);
}
