package com.wg.demo.controller;


import com.wg.demo.common.ResultMsg;
import com.wg.demo.common.interceptor.PageParam;
import com.wg.demo.common.util.IdWorker;
import com.wg.demo.dao.EmployeeMapper;
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

    @ApiOperation(value = "从数据源1查询")
    @GetMapping("getfromdb1")
    public ResultMsg getEmployeeFromDb1(Long id){
        return ResultMsg.getMsg(employeeMapper.selectFromDb1(id));
    }

    @ApiOperation(value = "从数据源2查询")
    @GetMapping("getfromdb2")
    public ResultMsg getEmployeeFromDb2(Long id){
        return ResultMsg.getMsg(employeeMapper.selectFromDb2(id));
    }


    @ApiOperation(value = "分页查询")
    @GetMapping("getEmployeeByPage")
    public ResultMsg getEmployeeByPage(Integer age,Integer pageNum,Integer paseSize){
        Map param = new HashMap<>();
        param.put("age",age);
        PageParam pageParam = new PageParam(pageNum,paseSize);
        param.put("pageParam", pageParam);
        List result = employeeMapper.getEmployeeByPage(param);
        return ResultMsg.getMsg(result,pageParam);
    }

}
