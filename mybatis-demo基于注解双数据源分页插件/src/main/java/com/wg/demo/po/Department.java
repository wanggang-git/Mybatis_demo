package com.wg.demo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Department implements Serializable {
    private Long id;

    private String deptName;

    private String descr;

    private Date createTime;

    List<Employee> employees;

}