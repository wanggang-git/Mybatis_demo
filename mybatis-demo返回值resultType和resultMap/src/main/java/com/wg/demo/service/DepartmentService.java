package com.wg.demo.service;

import com.wg.demo.dao.DepartmentMapper;
import com.wg.demo.po.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wanggang.io
 * @Date: 2019/6/27 16:06
 * @todo
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public int insertDept(Department department)
    {
        return departmentMapper.insertAutoId(department);
    }
}
