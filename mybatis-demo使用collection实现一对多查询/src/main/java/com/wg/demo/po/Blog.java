package com.wg.demo.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {
    private Long id;

    private String title;

    private Long userId;

    private String content;

    private Date createTime;

    List<Tag> tags;
}