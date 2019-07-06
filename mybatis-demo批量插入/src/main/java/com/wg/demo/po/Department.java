package com.wg.demo.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Department {
    private Long id;

    private String deptName;

    private String descr;

    private Date createTime;

    List<Employee> employees;

}