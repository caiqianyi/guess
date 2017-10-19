package com.caiqianyi.soa.core.exception;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caiqianyi.commons.exception.SuccessMessage;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Resource
	private ExceptionI18nProcessor exceptionI18nProcessor;
	
    @ExceptionHandler//处理所有异常
    public SuccessMessage exceptionHandler(Exception e, HttpServletResponse response) {
    	logger.error("error",e);
    	return exceptionI18nProcessor.exceptionHandler(e);
    }
    
}