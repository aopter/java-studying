package com.kai.mapper;

import com.kai.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
