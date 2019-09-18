package com.wg.demo.common.interceptor;

import com.wg.demo.common.datasource.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wanggang.io
 * @Date: 2019/8/29 8:52
 * @todo
 */
@Component
public class PageConfig {
    @Value("${spring.datasource.db1.dialect}")
    private String db1Dialect;
    @Value("${spring.datasource.db2.dialect}")
    private String db2Dialect;

    public String getDateSource() {
        return DataSourceContextHolder.getDB();
    }

    public static PageParam getPageParam(Integer pageNum, Integer pageSize) {
        if (pageSize == null || pageNum == null) {
            return null;
        }
        pageNum = pageNum > 0 ? pageNum : 1;
        pageSize = pageSize > 0 ? pageSize : 1;
        return new PageParam(pageNum, pageSize);
    }

    public static PageParam setPageParam(Integer pageNum, Integer pageSize, Map param) {
        PageParam pageParam = getPageParam(pageNum, pageSize);
        if (pageParam != null)
            param.put("pageParam", pageParam);
        return pageParam;
    }

    public String getDialect() {

        String dialect = getDialect(getDateSource());
        return dialect;
    }
    public String getDialect(String dbName){
        if(dbName.equals("db1"))
            return db1Dialect;
        else
            return db2Dialect;
    }

    public String getTotalSqlParam() {
        String sqlParma = " paging";
        if (getDialect().equals("postgre"))
            sqlParma = " as paging";
        else if (getDialect().equals("sqlserver")) {
            sqlParma = " as paging";
        }
        return sqlParma;
    }

    public String getSelectSqlParam(PageParam pageParam) {
        String sqlParma = " paging_table limit ?,?";
        String dialect = getDialect();
        if (dialect.equals("postgre")) {
            sqlParma = " paging_table limit ?  offset ?";
        } else if (dialect.equals("sqlserver")) {
            sqlParma = " as t_" + pageParam.getOrderColumn() + " order by " + pageParam.getOrderColumn() + " " + pageParam.getOrder() + " OFFSET ? ROWS\n" +
                    "            FETCH NEXT ? ROWS ONLY";
        }
        return sqlParma;
    }

    public Map getLimitParam(PageParam pageParam) {
        Integer pageSize = pageParam.getPageSize();
        Integer pageNum = pageParam.getPageNum() > 0 ? pageParam.getPageNum() : 1;
        Integer offset = (pageNum - 1) * pageSize;
        Map param = new HashMap();
        String dialect = this.getDialect();
        if (dialect.equals("postgre")) {
            param.put("first", pageSize);
            param.put("second", offset);
        } else if (dialect.equals("mysql") || dialect.equals("sqlserver")) {
            param.put("first", offset);
            param.put("second", pageSize);
        }
        return param;
    }
}
