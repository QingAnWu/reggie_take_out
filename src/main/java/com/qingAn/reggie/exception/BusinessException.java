package com.qingAn.reggie.exception;

/**
 * @author qingAn
 * @date 2022/08/23 20:00
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
