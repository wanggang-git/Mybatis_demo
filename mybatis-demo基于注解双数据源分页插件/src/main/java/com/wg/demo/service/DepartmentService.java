package com.wg.demo.service;

import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.DepartmentMapper;
import com.wg.demo.dao.EmployeeMapper;
import com.wg.demo.po.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wanggang.io
 * @Date: 2019/6/27 16:06
 * @todo
 */
@Service
public class DepartmentService {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private DepartmentMapper departmentMapper;

    public int insertDept(Department department)
    {
        try{
            return departmentMapper.insertAutoId(department);
        }catch (Exception e )
        {
            logger.info(e.toString());
            return -1;
        }
    }


    public int insertDepts(List<Department > departments)
    {
        try{
            return departmentMapper.insertDepartments(departments);
        }catch (Exception e )
        {
            logger.info(e.toString());
            return -1;
        }
    }
}
