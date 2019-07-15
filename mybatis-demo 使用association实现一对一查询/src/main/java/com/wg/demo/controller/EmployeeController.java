package com.wg.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.EmployeeMapper;
import com.wg.demo.po.Department;
import com.wg.demo.po.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wanggang.io
 * @Date: 2019/2/2 10:38
 * @todo
 */
@Api(description = "employee相关接口")
@RestController
@RequestMapping("employee")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private EmployeeMapper employeeMapper;

    @ApiOperation(value = "按id查询员工")
    @GetMapping("get/{id}")
    public ResultMsg getById(@PathVariable(value="id") Long id){

        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return ResultMsg.getMsg(employee);
    }

    @ApiOperation(value = "删除员工")
    @PostMapping("del/{id}")
    public ResultMsg delById(@PathVariable(value="id") Long id){
        int result = employeeMapper.deleteByPrimaryKey(id);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "新增员工")
    @PostMapping("new")
    public ResultMsg newEmployee(@RequestBody Employee employee){
        employee.setId(idWorker.nextId());
        int result = employeeMapper.insert(employee);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "更新员工信息")
    @PostMapping("update")
    public ResultMsg updateEmployee(@RequestBody Employee employee){
        int result = employeeMapper.updateByPrimaryKeySelective(employee);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("findBypaging")
    public ResultMsg findByPaging(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> data = employeeMapper.findByPaging();
        JSONObject result = new JSONObject();
        result.put("employees",data);
        result.put("pages",data.getPages());
        result.put("total",data.getTotal());
        return ResultMsg.getMsg(result);
    }


    @ApiOperation(value = "多个参数查询_匿名顺序传参")
    @GetMapping("findByParams")
    public ResultMsg findByParams(Short gender,String age)
    {
        List result= employeeMapper.selectByGenderAndAge(gender,age);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "多个参数查询_注解方式传参")
    @GetMapping("findByParams2")
    public ResultMsg findByParams2(Short gender,String age)
    {
        List result= employeeMapper.selectByGenderAndAge2(gender,age);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "多个参数查询_map传参")
    @GetMapping("findByMapParams")
    public ResultMsg findByMapParams(Short gender,String age)
    {
        Map params = new HashMap<>();
        params.put("gender",gender);
        params.put("age",age);
        List result= employeeMapper.selectByMapParams(params);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "多个参数查询_通过Java Bean传递多个参数")
    @GetMapping("findByBeans")
    public ResultMsg findByBeans(@RequestBody Employee employee)
    {
        List result= employeeMapper.selectByBeans(employee);
        return ResultMsg.getMsg(result);
    }
    @ApiOperation(value = "多个参数查询_通过JSON传递多个参数")
    @GetMapping("findByJSONObject")
    public ResultMsg findByJSONObject(@RequestBody JSONObject params)
    {
        List result= employeeMapper.findByJSONObject(params);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "多个参数查询_通过JSON传递多个参数")
    @GetMapping("findByList")
    public ResultMsg findByList(@RequestBody List<String> list)
    {
        List result= employeeMapper.findByList (list);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "多个参数查询_对象+集合参数")
    @GetMapping("findByDepartment")
    public ResultMsg findByDepartment(@RequestBody Department department)
    {
        List result= employeeMapper.findByDepartment(department);
        return ResultMsg.getMsg(result);
    }

    @ApiOperation(value = "返回JSONObject")
    @GetMapping ("findJSONObject")
    public ResultMsg findJSONObject( )
    {
        return ResultMsg.getMsg(employeeMapper.findJSONResult());
    }

    @ApiOperation(value = "assocation_关联查询")
    @GetMapping("getAssocation")
    public ResultMsg getAssociation(Long employeeId)
    {
        return ResultMsg.getMsg(employeeMapper.getAssociation(employeeId));
    }

    @ApiOperation(value = "assocation_分步查询")
    @GetMapping("getAssocation_step")
    public ResultMsg getAssociationByStep(Long employeeId)
    {

        return null;

    }
}
