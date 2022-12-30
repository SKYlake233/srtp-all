package com.example.srtp.Common.Exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException{
    private String code;

    public MyException(String code , String msg){
        super(msg);
        this.code = code;
    }
}
