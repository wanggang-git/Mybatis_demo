package com.wg.demo.dao;

import com.wg.demo.po.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //List<Role > selectRoleAndUserInfo();

    //Role selectRoleAndUserInfo_step(Long roleId);

}