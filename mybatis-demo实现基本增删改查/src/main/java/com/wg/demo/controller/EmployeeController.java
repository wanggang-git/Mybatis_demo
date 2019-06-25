package com.wg.demo.controller;


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
        Page<Employee> result = employeeMapper.findByPaging();
        int pages = result.getPages();
        Long total = result.getTotal();
        return ResultMsg.getMsg(result);

    }


}
