package com.kai.dao;

import com.kai.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:11
 * @projectName mybatis-study
 * @className TeacherMapper.java
 * @description TODO
 */
public interface TeacherMapper {


    @Select("select * from teacher where id = #{tid}")
    Teacher getTeacherById(@Param("tid") int id);
}
