package com.kai.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 刘凯丽
 * @createTime 2021/2/2 15:43
 * @projectName mybatis-study
 * @className Blog.java
 * @description TODO
 */
@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private int views;
    private Date create_time;//和数据库命名不一致 下划线转为驼峰
}
