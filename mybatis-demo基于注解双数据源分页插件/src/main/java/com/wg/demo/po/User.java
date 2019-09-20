package com.wg.demo.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;

    private String nickName;

    private String loginName;

    private String password;

    private Date createTime;

    private Long roleId;

   // private Role role;

}