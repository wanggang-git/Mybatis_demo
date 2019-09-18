package com.wg.demo.common.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wanggang.io
 * @Date: 2018/9/5 10:06
 * @todo
 */
@Component
public class LocalConfig {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${spring.datasource.db1.dialect}")
    private String db1Dialect;
    @Value("${spring.datasource.db2.dialect}")
    private String db2Dialect;
//
//    public String getDialect(String dbName){
//        if(dbName.equals("db1"))
//            return db1Dialect;
//        else
//            return db2Dialect;
//    }

    public LocalConfig() {
        //sessionIds = jsessionids + dbname;
    }

}
