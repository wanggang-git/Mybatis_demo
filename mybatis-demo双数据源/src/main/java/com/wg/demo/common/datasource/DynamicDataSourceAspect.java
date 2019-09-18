package com.wg.demo.common.datasource;

import com.wg.demo.common.util.FunUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: wanggang.io
 * @Date: 2018/12/28 15:50
 * @todo自定义注解 + AOP的方式实现数据源动态切换。
 */

@Aspect
@Component
public class DynamicDataSourceAspect {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    public DynamicDataSourceAspect() {
        int a = 1;
    }

    @Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point) {
        try {
            Object methodInvocation = FunUtil.getPrivateField(point, "methodInvocation", point.getClass());
            Method method = (Method) FunUtil.getPrivateField(methodInvocation, "method", methodInvocation.getClass());
            DS annotation = method.getAnnotation(DS.class);
            DataSourceContextHolder.setDB(annotation.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("当前数据源为" + DataSourceContextHolder.getDB());
    }

    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}
