package com.wg.demo.dao;

import com.wg.demo.po.TagAndBlog;

public interface TagAndBlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TagAndBlog record);

    int insertSelective(TagAndBlog record);

    TagAndBlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TagAndBlog record);

    int updateByPrimaryKey(TagAndBlog record);
}