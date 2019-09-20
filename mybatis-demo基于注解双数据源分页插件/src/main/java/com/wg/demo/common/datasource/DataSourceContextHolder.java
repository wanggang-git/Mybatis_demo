package com.wg.demo.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wanggang.io
 * @Date: 2018/12/28 9:50
 * @todo
 */
public class DataSourceContextHolder {
    static  Logger  logger = LoggerFactory.getLogger("DataSourceContextHolder");
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = DataSourceEnum.DB1.getValue();

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        String dbsource = contextHolder.get();
        if(dbsource == null ){
            dbsource = "db1";
            logger.info("数据源为NULL 返回默认数据源"+ dbsource);
        }
        return  dbsource;
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}

