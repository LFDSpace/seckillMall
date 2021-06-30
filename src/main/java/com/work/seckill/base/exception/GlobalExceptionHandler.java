package com.work.seckill.base.exception;

import com.work.seckill.base.result.CodeMsg;
import com.work.seckill.base.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// @RestControllerAdvice 统一异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {
//    Spring的@ExceptionHandler可以用来统一处理方法抛出的异常
    @ExceptionHandler(value = Exception.class)//=Exception.class拦截所有异常，可以自定义其他
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        //instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例
        if(e instanceof GlobalException){ // 业务异常
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsg());
        }else if(e instanceof BindException){ //绑定异常
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors(); // 使用数组是因为异常可能不止一条
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR);
        }else{
            return Result.error(CodeMsg.SERVER_ERROR); // 通用服务异常
        }
    }
}
