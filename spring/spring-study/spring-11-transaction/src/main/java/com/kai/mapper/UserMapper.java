package com.kai.mapper;

import com.kai.pojo.User;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 18:06
 * @projectName spring-study
 * @className UserMapper.java
 * @description TODO
 */
public interface UserMapper {
    public List<User> selectUser();
    public int addUser(User user);
    public int deleteUser(int id);

}
