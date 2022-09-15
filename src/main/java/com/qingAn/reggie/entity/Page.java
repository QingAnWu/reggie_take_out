package com.qingAn.reggie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("页面")
public class Page<T> {
    /**
     * 当前页数据列表
     */
    @ApiModelProperty("当前页数据列表")
    protected List<T> records;
    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    protected long total;
    /**
     * 每页大小
     */
    @ApiModelProperty("每页大小")
    protected long pageSize;
    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    protected long page;

}
