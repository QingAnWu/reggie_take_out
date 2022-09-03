package com.qingAn.reggie.mapper;

import com.qingAn.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qingAn
 * @date 2022/09/03 17:04
 */
@Repository
public interface AddressBookMapper {

    /**
     * 添加地址
     *
     * @param addressBook
     */
    @Insert("insert into address_book(user_id,consignee,phone,sex,detail,label,create_time,update_time," +
            "create_user,update_user) " +
            "values(#{userId},#{consignee},#{phone},#{sex},#{detail},#{label},#{createTime},#{updateTime},#{createUser}," +
            "#{updateUser})")
    void save(AddressBook addressBook);

    /**
     * 查询地址列表
     *
     * @param userId
     * @return
     */
    @Select("select * from address_book where user_id=#{userId} and is_deleted=0 order by update_time desc")
    List<AddressBook> queryAddressList(Long userId);

    @Update("update address_book set is_default=0 where user_id=#{userId}")
    void removeDefaultAddress(Long userId);

    @Update("update address_book set is_default=1 where id=#{id}")
    void updateDefaultAddress(AddressBook addressBook);

    @Select("select * from address_book where id=#{id} and is_deleted=0" )
    AddressBook getById(Long id);

    @Select("select * from address_book where user_id = #{userId} and is_default = 1 and is_deleted=0")
    AddressBook getDefaultAddress(Long userId);

    /**
     * 逻辑删除 与 修改
     *
     * @param user
     */
    void updateDeleteAddressBook(AddressBook addressBook);
}