package com.kai.dao;

import com.kai.pojo.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @createTime 2021/1/29 18:18
 * @projectName mybatis-study
 * @className UserDao.java
 * @description TODO
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

//    根据id查询用户 多个参数时，每个参数都需要加@param 引用类型不需要
    @Select("select * from user where id = #{id}")
    List<User>  getUserById(@Param("id") int id);

//    增加
    @Insert("insert into user(id,name,pwd) value (#{id},#{name},#{pwd})")
    int addUser(User user);
//    删除
    @Delete("delete from user where id = #{id}")
    int deleteUser(@Param("id") int id);
//    更改
    @Update("update user set name=#{name},pwd=#{pwd} where id = #{id}")
    int updateUser(User user);
}
