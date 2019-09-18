package com.wg.demo.dao;

import com.wg.demo.po.Employee;
import com.wg.demo.po.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

//    User  getUserInfo_Association(Long id);
//    User getUserInfo_Association_step(Long id);
    List<User> getUserInfoByRoleId(Long roleId);

    List selectUserInfo();

}