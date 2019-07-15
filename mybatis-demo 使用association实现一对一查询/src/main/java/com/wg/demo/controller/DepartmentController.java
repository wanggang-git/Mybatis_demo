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

    @ApiOperation(value = "新增部门")
    @PostMapping("new")
    public ResultMsg newDepartment(@RequestBody Department department) {
        department.setId(idWorker.nextId());
        int result = departmentService.insertDept(department);

        return ResultMsg.getStrMsg(result > 0 ? "SUCCESS" : "FAILED");
    }

    @ApiOperation(value = "批量插入_新增多个部门")
    @PostMapping("insertDepartments")
    public ResultMsg newDepartment(@RequestBody List<Department > departments ) {
        int result = departmentService.insertDepts(departments);
        return ResultMsg.getStrMsg(result > 0 ? "SUCCESS" : "FAILED");
    }

    @ApiOperation(value = "返回参数_返回一般数据类型")
    @PostMapping("findDeptName")
    public ResultMsg findDeptName(Long id ) {
        String result = departmentMapper.getDeptName(id);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "返回参数_返回javaBean")
    @PostMapping("findDeptmen")
    public ResultMsg findDeptmen(Long id ) {
        Department result = departmentMapper.getDeptById(id);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "返回参数_返回map,单个对象")
    @PostMapping("findDeptAsMap")
    public ResultMsg findDeptAsMap(Long id ) {
        Map result = departmentMapper.getDeptAsMap(id);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "返回参数_返回javaBean")
    @PostMapping("findDeptAsMaps")
    public ResultMsg findDeptAsMaps() {
        Map result = departmentMapper.getDeptAsMaps();
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "返回参数_返回一对多结构")
    @PostMapping("findByAssocation")
    public ResultMsg findByAssocation() {
        return ResultMsg.getMsg(departmentMapper.getDeptWithEmployees());
    }
}
