package com.qingAn.reggie.common;

import io.swagger.annotations.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 *
 * @param <T>
 * @author qingAn
 */
@Data
@ApiModel("封装类")
public class R<T> {
    /**
     * 编码：1成功，0和其它数字为失败
     */
    @ApiModelProperty("编码")
    private Integer code;
    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String msg;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;
    /**
     * 动态数据
     */
    @ApiModelProperty("动态数据")
    private Map map = new HashMap();

    /**
     * 如果业务执行结果为成功, 构建R对象时, 只需要调用 success 方法;
     * 如果需要返回数据传递 object 参数, 如果无需返回, 可以直接传递null。
     * @param object 数据传递
     * @param <T> 泛型
     * @return 返回服务端响应的数据
     */
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    /**
     * 如果业务执行结果为失败, 构建R对象时, 只需要调用error 方法, 传递错误提示信息即可。
     * @param msg 错误信息
     * @param <T> 泛型
     * @return 返回服务端响应的数据
     */
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}