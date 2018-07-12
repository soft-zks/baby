package com.hubu.baby.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 全局异常处理
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-16 21:41
 * @Since: version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 基类异常处理
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BaseException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
