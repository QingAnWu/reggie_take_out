package com.qingAn.reggie.controller;


import com.qingAn.reggie.common.R;
import com.qingAn.reggie.entity.AddressBook;
import com.qingAn.reggie.entity.User;
import com.qingAn.reggie.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 地址簿管理
 *
 * @author qingAn
 * @date 2022/09/03 17:04
 */
@Slf4j
@RestController
@Api(value = "/addressBook",tags = {"地址簿控制器"})
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook, HttpSession session) {
        User user = (User) session.getAttribute("user");
        addressBook.setUserId(user.getId());
        addressBook.setCreateUser(user.getId());
        addressBook.setUpdateUser(user.getId());
        addressBook.setCreateTime(LocalDateTime.now());
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R<Object> get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<AddressBook> getDefault(HttpSession session) {
        User user = (User) session.getAttribute("user");
        AddressBook addressBook = addressBookService.getDefaultAddress(user.getId());

        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }


    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook, HttpSession session) {
        User user = (User) session.getAttribute("user");
        addressBook.setUserId(user.getId());
        addressBookService.updateDefaultAddress(addressBook);
        return R.success(addressBook);
    }


    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<AddressBook> addressList = addressBookService.queryAddressList(user.getId());
        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressList);
    }

    @DeleteMapping
    public R<String> delete(Long ids ,HttpSession session) {
        User user = (User) session.getAttribute("user");
        AddressBook addressBook = new AddressBook();
        addressBook.setIsDeleted(1);
        addressBook.setId(ids);
        addressBook.setUpdateUser(user.getId());
        addressBookService.updateDeleteAddressBook(addressBook);
        return R.success("成功删除");
    }

    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook,HttpSession session) {
        User user = (User) session.getAttribute("user");
        addressBook.setUpdateUser(user.getId());
        addressBookService.updateDeleteAddressBook(addressBook);
        return  R.success("成功删除");
    }

}