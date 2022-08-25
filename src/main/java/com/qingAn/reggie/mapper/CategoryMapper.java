package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/08/25 9:15
 */
@Repository
public interface CategoryMapper {

    /**
     * 根据类别名查找一个类别信息
     *
     * @param name
     * @return
     */
    @Select("select * from category where name =#{name}")
    Category inquiry(String name);

    /**
     * 添加类别信息
     *
     * @param category
     */
    @Insert("insert into category values (null,#{type},#{name},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void saveCategory(Category category);

    /**
     * 查询所有的类别
     *
     * @return
     */
    @Select("select * from category order by sort")
    List<Category> findAll();

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    @Update("update category set type=#{type},name=#{name},sort=#{sort},update_time=#{updateTime},update_user=#{updateUser} where id=#{id}")
    void updateById(Category category);
}
