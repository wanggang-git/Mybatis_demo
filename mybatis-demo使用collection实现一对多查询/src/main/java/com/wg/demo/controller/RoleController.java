package com.wg.demo.controller;

import com.wg.demo.common.ResultMsg;
import com.wg.demo.dao.RoleMapper;
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
@Api(description = "角色相关接口")
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;
    @ApiOperation(value = "查询角色_使用collection实现一对多查询")
    @GetMapping("getById{roleId}")
    public ResultMsg getById(@PathVariable(value="roleId") Long roleId){
        return ResultMsg.getMsg(roleMapper.selectRoleAndUserInfo(roleId));
    }


    @ApiOperation(value = "查询角色_使用collection_分步查询_实现一对多查询")
    @GetMapping("getByIdByStep{roleId}")
    public ResultMsg getByIdByStep(@PathVariable(value="roleId") Long roleId){
        return ResultMsg.getMsg(roleMapper.selectRoleAndUserInfo_step(roleId));
    }
}
