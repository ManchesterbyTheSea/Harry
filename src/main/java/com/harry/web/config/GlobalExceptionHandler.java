package com.harry.web.config;

import com.harry.web.utils.ApiResult;
import com.harry.web.utils.ApiResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一异常处理
 * Controller不处理异常
 * Created by chenhaibo on 2017/11/9.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult<Object> handleException(HttpServletRequest request, Exception ex) {
        /*
        以后留着些resultAPI
         */
        ApiResult<Object> result = new ApiResult<Object>();
        result.setCode(ApiResultCode.C500.getCode());
        result.setInfo(ApiResultCode.C500.getDesc());
        logger.error(ex.getMessage());
        return result;
    }
}
