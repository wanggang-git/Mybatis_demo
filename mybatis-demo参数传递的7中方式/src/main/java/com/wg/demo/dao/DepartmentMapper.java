package com.wg.demo.dao;

import com.wg.demo.po.Department;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertAutoId(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int insertDepartments(List<Department> list);

    int insertDepartments2(List<Department> list);

    String getDeptName(Long id);

    Department getDeptById(Long id);

    Map getDeptAsMap(Long id);
    @MapKey("id")
    Map getDeptAsMaps();

    List<Department> selectAll();

    List getDeptWithEmployees();

}