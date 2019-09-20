package com.wg.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wg.demo.common.datasource.DS;
import com.wg.demo.po.Department;
import com.wg.demo.po.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);


    @DS("db1")
    Employee selectFromDb1(Long id);

    @DS("db2")
    Employee selectFromDb2(Long id);

    @DS("db2")
    List getEmployeeByPage(Map param);
}