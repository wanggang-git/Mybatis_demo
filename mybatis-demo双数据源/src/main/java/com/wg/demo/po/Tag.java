package com.wg.demo.po;

import lombok.Data;

import java.util.List;

@Data
public class Tag {
    private Long id;

    private String name;

    List<Blog> blogs;

}