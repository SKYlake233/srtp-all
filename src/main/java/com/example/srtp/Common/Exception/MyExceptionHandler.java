package com.example.srtp.Common.Exception;

import com.example.srtp.Common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handle(MyException use){
        return Result.error(use.getCode(), use.getMessage());
    }
}
