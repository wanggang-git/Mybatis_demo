package com.wg.demo.dao;

import com.github.pagehelper.Page;
import com.wg.demo.po.Employee;

import java.util.Map;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Page<Employee> findByPaging(Map param);
}