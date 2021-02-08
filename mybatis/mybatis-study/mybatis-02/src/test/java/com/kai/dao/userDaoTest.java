package com.kai.dao;

import com.kai.pojo.User;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @createTime 2021/1/29 18:43
 * @projectName mybatis-study
 * @className userDaoTest.java
 * @description TODO
 */
public class userDaoTest {

    @Test
    public void test() {
//        1.获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        2.执行sql  方式一：getmapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
//      遍历
        for (User user : users) {
            System.out.println(user);
        }
//        关闭
        sqlSession.close();
    }



}



