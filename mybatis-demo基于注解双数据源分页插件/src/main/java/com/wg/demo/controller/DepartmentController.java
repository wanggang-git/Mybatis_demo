package com.wg.demo.controller;

import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.DepartmentMapper;
import com.wg.demo.po.Department;
import com.wg.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: wanggang.io
 * @Date: 2019/6/27 16:08
 * @todo
 */
@Api(description = "department")
@RestController
@RequestMapping("dept")
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;
    @Autowired
    public IdWorker idWorker;

    @Autowired
    private DepartmentMapper departmentMapper;



}
