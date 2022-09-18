package com.qingAn.reggie.service;


import com.qingAn.reggie.entity.AddressBook;

import java.util.List;

public interface AddressBookService  {

    /**
     * 保存地址
     * @param addressBook
     */
    void save(AddressBook addressBook);

    /**
     * 查询地址对象
     * @param currentId
     * @return
     */
    List<AddressBook> queryAddressList(Long currentId);

    /**
     * 更新默认地址
     * @param addressBook
     */
    void updateDefaultAddress(AddressBook addressBook);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 得到默认地址
     * @param currentId
     * @return
     */
    AddressBook getDefaultAddress(Long currentId);

    /**
     * 逻辑删除与修改
     */
    void updateDeleteAddressBook(AddressBook addressBook);
}