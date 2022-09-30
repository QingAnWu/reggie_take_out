package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Setmeal;
import com.qingAn.reggie.entity.SetmealDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/25 9:15
 */
@Repository
public interface SetMealMapper {


    /**
     * 根据类别的id查找套餐总数
     *
     * @param id
     * @return
     */
    @Select("select count(0) from setmeal where category_id=#{id}")
    long findSetMealByCategoryId(Long id);

    @Insert("insert into setmeal values(null,#{categoryId},#{name},#{price},#{status},#{code},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser},0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(SetmealDto setmealDto);

    /**
     * 根据名字查找套餐
     */
    List<Setmeal> findByName(@Param("name") String name);

    /**
     * 根据套餐的类别展示套餐
     * @param categoryId
     * @param status
     * @return
     */
    List<Setmeal> findByCategoryId(@Param("categoryId") Long categoryId,@Param("status") Integer status);

    /**
     * 根据套餐的id，查询状态为1的总数.
     *
     * @param ids
     * @return
     */
    Long queryDishWithStatus(@Param("ids") List<Long> ids);

    /**
     * 根据套餐的id删除套餐
     *
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Long> ids, @Param("opr") Long opr, @Param("updateTime") LocalDateTime updateTime);

    void updateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status, @Param("opr") Long opr, @Param("updateTime") LocalDateTime updateTime);

    Setmeal queryId(long id);

    void update(SetmealDto setmealDto);
}
