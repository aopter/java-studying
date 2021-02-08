package com.kai.dao;

import com.kai.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:11
 * @projectName mybatis-study
 * @className TeacherMapper.java
 * @description TODO
 */
public interface TeacherMapper {

//    获取指定老师的学生信息 以及老师信息
    Teacher getTeacherById(int id);

    @Select("select * from teacher")
    List<Teacher> getTeacher();


}
