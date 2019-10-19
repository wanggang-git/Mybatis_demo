package com.wg.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.EmployeeMapper;
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


    @ApiOperation(value = "返回String")
    @GetMapping("returnString")
    public ResultMsg returnString(Long id){
        String  name = employeeMapper.getNameById(id);
        return ResultMsg.getMsg(name);
    }

    @ApiOperation(value = "返回Integer")
    @GetMapping("returnInteger")
    public ResultMsg returnInteger(Long id){
        Integer age = employeeMapper.getAgeById(id);
        return ResultMsg.getMsg(age);
    }


    @ApiOperation(value = "返回bean")
    @GetMapping("returnBean")
    public ResultMsg returnBean(Long id){
        Employee e = employeeMapper.selectByPrimaryKey(id);
        return ResultMsg.getMsg(e);
    }


    @ApiOperation(value = "返回list")
    @GetMapping("returnList")
    public ResultMsg returnList(){
        List r = employeeMapper.getAllEmpsAsList();
        return ResultMsg.getMsg(r);
    }

    @ApiOperation(value = "以Map形式返回一条数据")
    @GetMapping("returnEmpAsMap")
    public ResultMsg returnEmpAsMap(Long id){
        Map m = employeeMapper.getEmpAsMap(id);
        return ResultMsg.getMsg(m);
    }

    @ApiOperation(value = "以Map形式返回多条数据")
    @GetMapping("returnEmpsAsMap")
    public ResultMsg returnEmpsAsMap(){
        Map m = employeeMapper.getAllEmpsAsMap();
        return ResultMsg.getMsg(m);
    }
}
