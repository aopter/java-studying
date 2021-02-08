package com.kai.dao;

import com.kai.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author 刘凯丽
 * @createTime 2021/1/29 18:18
 * @projectName mybatis-study
 * @className UserDao.java
 * @description TODO
 */
public interface UserMapper {
    List<User> getUsers();
//  根据id查询
    User getUserById(int id);
//    模糊查询
    List<User> getUsersByLike(String value);
//  添加
    int addUser(User user);
//  更新
    int updateUser(User user);
//   删除
    int deleteUserById(int id);
}
