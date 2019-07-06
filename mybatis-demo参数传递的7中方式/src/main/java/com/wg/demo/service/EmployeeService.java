package com.wg.demo.service;

import com.wg.demo.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wanggang.io
 * @Date: 2019/6/27 16:06
 * @todo
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


}
