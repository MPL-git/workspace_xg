package com.jf.common.ext.interceptor;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.StringUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author luoyb
 * Created on 2019/9/21
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResponseMsg handleBizException(BizException e) {
        return ResponseMsg.error(e.getErrorCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ArgException.class)
    public ResponseMsg handle(ArgException e) {
        return ResponseMsg.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResponseMsg handleBindException(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseMsg.error(ResponseMsg.ERROR, "请求参数错误：" + fieldError.getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMsg handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseMsg.error(ResponseMsg.ERROR, "请求参数错误：" + fieldError.getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, TypeMismatchException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseMsg methodNotAllowed(Exception e) {
        e.printStackTrace();
        return ResponseMsg.error(ResponseMsg.ERROR, StringUtil.buildMsg("请求地址或请求方法异常：{}", e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseMsg error(Throwable e) {
        e.printStackTrace();
        return ResponseMsg.error(ResponseMsg.ERROR, "未知错误，请联系客服处理");
    }

}
