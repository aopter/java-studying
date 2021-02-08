package com.kai.mapper;

import com.kai.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/8 12:34
 * @projectName spring-study
 * @className UserMapperImpl2.java
 * @description TODO
 */


public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{


    public List<User> selectUser() {

        User user = new User(77, "xiaowang", "444");
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.addUser(user);
        mapper.deleteUser(77);
        return mapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
