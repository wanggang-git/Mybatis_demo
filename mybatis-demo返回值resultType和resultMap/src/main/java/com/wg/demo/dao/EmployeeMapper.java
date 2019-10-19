package com.wg.demo.dao;

import com.github.pagehelper.Page;
import com.wg.demo.po.Employee;
import org.apache.ibatis.annotations.MapKey;

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

    List<Employee> testWhere(Map params);

    String getNameById(Long id);

    Integer getAgeById(Long id);

    Employee getEmpAsBean(Long id );

    List<Employee>getAllEmpsAsList();

    Map getEmpAsMap(Long id );
    @MapKey("id")
    Map<Long, Employee> getAllEmpsAsMap();
}