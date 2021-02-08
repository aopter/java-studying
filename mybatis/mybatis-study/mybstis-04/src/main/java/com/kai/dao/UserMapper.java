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
//    分页查询
    List<User> getUsersByLimit(Map<String,Integer> map);
//    Rowbound 分页查询
    List<User> getUsersByRowBounds();

}
