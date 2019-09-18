package com.wg.demo.common.datasource;
/**
 * @Author: wanggang.io
 * @Date: 2018/12/28 9:50
 * @todo
 */
public enum DataSourceEnum {

    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
