package com.wg.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wg.demo.po.Department;
import com.wg.demo.po.Employee;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Page<Employee> findByPaging();

    List<Employee> selectByGenderAndAge(   Short gender,  String age );

    List<Employee> selectByGenderAndAge2( @Param("gender")  Short gender,  @Param("age") String age );

    List<Employee> selectByMapParams(Map params);

    List <Employee> selectByBeans(Employee employee);

    List <Employee> findByJSONObject(JSONObject params);

    List <Employee> findByList(List list);

    List <Employee> findByDepartment(@Param("department")Department department);

    JSONArray findJSONResult();

    Employee getAssociation(Long employeeId);

}