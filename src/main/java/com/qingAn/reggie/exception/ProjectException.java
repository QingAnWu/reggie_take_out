package com.qingAn.reggie.exception;

import com.qingAn.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author qingAn
 * @date 2022/08/23 20:00
 */
@RestControllerAdvice
@Slf4j
public class ProjectException{

    @ExceptionHandler(BusinessException.class)
    public R<String> doBusinessException(Exception ex){
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public R<String> doSystemException(Exception ex){
        log.error("========异常信息======");
        log.error(ex.getMessage(),ex);
        return R.error("预期的服务器异常，msg:"+ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> doException(Exception ex){
        log.error("========异常信息======");
        log.error(ex.getMessage(),ex);
        return R.error("未预期服务器异常:请联系管理员");
    }
}
