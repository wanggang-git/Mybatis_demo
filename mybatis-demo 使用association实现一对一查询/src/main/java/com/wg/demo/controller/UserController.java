package com.wg.demo.controller;

import com.wg.demo.common.ResultMsg;
import com.wg.demo.dao.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wanggang.io
 * @Date: 2019/7/10 11:54
 * @todo
 */
@Api(description = "用户相关接口")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "查询用户_使用association实现一对一查询")
    @GetMapping("get{id}")
    public ResultMsg getById(@PathVariable(value="id") Long id){
        return ResultMsg.getMsg(userMapper.getUserInfo_Association(id));
    }
    @ApiOperation(value = "查询用户_使用association实现一对一分步查询")
    @GetMapping("getBystep{id}")
    public ResultMsg getByStep( @PathVariable(value="id") Long id ){
        return ResultMsg.getMsg(userMapper.getUserInfo_Association_step(id));
    }
}
