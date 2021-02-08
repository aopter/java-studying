package com.kai.dao;

import com.kai.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author 刘凯丽
 * @createTime 2021/2/2 15:46
 * @projectName mybatis-study
 * @className BlogMapper.java
 * @description TODO
 */
public interface BlogMapper {

    int addBlog(Blog blog);
//    查询博客
    List<Blog> queryBlogs(Map<String,String> map);
}
