package com.qingAn.reggie.exception;

/**
 * @author qingAn
 * @date 2022/08/23 20:00
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
