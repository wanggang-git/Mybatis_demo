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
        Map param =  getLimitParam(pageParam);
        //默认方言为mysql
        String sqlParma = " paging_table limit "+(Integer)param.get("first") + " , " +(Integer)param.get("second");
        String dialect = getDialect();
        if (dialect.equals("postgre")) {
            sqlParma = " paging_table limit "+(Integer)param.get("first") + "  offset " +(Integer)param.get("second");
        } else if (dialect.equals("sqlserver")) {
            //sqlserver 分页需要提供一个字段名 作为order by的参数
            sqlParma = " as t_" + pageParam.getOrderColumn() + " order by " + pageParam.getOrderColumn() + " " + pageParam.getOrder() + " OFFSET "+(Integer)param.get("first")+" ROWS\n" +
                    "            FETCH NEXT " +(Integer)param.get("second") +" ROWS ONLY";
        }
        else if(dialect.equals("mysql")){
            return sqlParma;
        }
        return sqlParma;
    }
    /**
     * 获取用于分页的参数，
     * 一个为pageSize 分页大小
     * 一个为offser(位移) = (pageNum-1)* pageSize
     * 不同数据库方言语法不同，所以用map做了映射
     * 方便sql拼装
     */

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
