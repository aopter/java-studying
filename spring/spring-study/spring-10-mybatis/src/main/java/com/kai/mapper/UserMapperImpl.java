package com.kai.mapper;

import com.kai.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/8 12:03
 * @projectName spring-study
 * @className UserMapperImpl.java
 * @description TODO
 */
//我们原来所有操作都使用sqlSession ，现在 使用sqlSessionTemplate执行

    @Repository
public class UserMapperImpl implements UserMapper{
    private SqlSessionTemplate sqlSession;
    @Autowired//注入
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
