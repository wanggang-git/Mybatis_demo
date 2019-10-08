package com.wg.demo.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


@Aspect
@Component
public class HttpAspect {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    private ObjectError error;


    @Pointcut("execution(* com.wg.demo.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        logger.info("-------------------用户发起请求-----------------");
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        //如果是表单，参数值是普通键值对。如果是application/json，则request.getParameter是取不到的。
//        logger.info("HTTP_HEAD Type : " + request.getHeader("Content-Type"));
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//
//        if ("application/json".equals(request.getHeader("Content-Type"))) {
//            //记录application/json时的传参，SpringMVC中使用@RequestBody接收的值
//            logger.info(getRequestPayload(request));
//        } else {
//            //记录请求的键值对
//            for (String key : request.getParameterMap().keySet()) {
//                logger.info(key + "----" + request.getParameter(key));
//            }
//        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        //logger.info("方法的返回值 : " + JSON.toJSONString(ret,true));
        logger.info("方法的返回值 : " + ret.toString());
        logger.info("------------------请求结束------------------");
    }

    //后置异常通知
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void throwss(JoinPoint jp, Exception ex) {
        logger.info("方法异常时执行....." + ex.getMessage());
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp) {
//        logger.info("方法最后执行.....");
    }


    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
