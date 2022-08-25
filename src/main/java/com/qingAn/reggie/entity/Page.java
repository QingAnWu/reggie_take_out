package com.qingAn.reggie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 页面
 *
 * @author qingAn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    /**
     * 当前页数据列表
     */
    protected List<T> records;
    /**
     * 总条数
     */
    protected long total;
    /**
     * 每页大小
     */
    protected long pageSize;
    /**
     * 当前页
     */
    protected long page;

}
