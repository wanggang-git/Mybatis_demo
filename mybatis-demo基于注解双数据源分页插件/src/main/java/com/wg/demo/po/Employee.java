package com.wg.demo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Employee implements Serializable {
    private Long id;

    private String name;

    private String age;

    private Short gender;

    private Long deptId;

    private String address;

    private Date createTime;



}