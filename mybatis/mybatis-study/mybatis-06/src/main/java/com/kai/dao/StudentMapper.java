package com.kai.dao;

import com.kai.pojo.Student;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:11
 * @projectName mybatis-study
 * @className StudentMapper.java
 * @description TODO
 */
public interface StudentMapper {
//    多对一联系
//    查询所有学生信息和学生对应的老师信息
    List<Student> getStudentInfo();
    List<Student> getStudentInfo2();
}
