package com.hubu.baby.exception;

/**
 * @Description: 自定义全局异常处理基类
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-16 21:44
 * @Since: version 1.0
 **/
public class BaseException extends Exception{

    public BaseException(String message) {
        super(message);
    }
}
