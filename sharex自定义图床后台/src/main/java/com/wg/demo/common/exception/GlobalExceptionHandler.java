package com.wg.demo.common.exception;

import com.wg.demo.common.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMsg jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResultMsg r = new ResultMsg();
        logger.info(e.getMessage());
        r.setResultMsg(e.getMessage());
        r.setResult("FAILED");
        r.setResultCode(500);
        e.printStackTrace();
        return r;
    }

}
