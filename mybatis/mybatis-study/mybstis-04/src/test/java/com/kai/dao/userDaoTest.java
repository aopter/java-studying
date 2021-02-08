package com.kai.dao;

import com.kai.pojo.User;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
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

   static Logger logger = Logger.getLogger(userDaoTest.class);


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

    @Test
    public void testLog4j(){
        logger.info("info:进入");
        logger.error("error:进入");
        logger.debug("debug:进入");
    }

    @Test
    public void testLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",1);
        map.put("pageSize",3);

        List<User> users = mapper.getUsersByLimit(map);

        for(User user:users){
            logger.info(user);
        }
        sqlSession.close();
    }


    @Test
    public void testRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        RowBounds rowBounds = new RowBounds(1, 2);

//      通过java代码分页
        List<User> users = sqlSession.selectList("com.kai.dao.UserMapper.getUsersByRowBounds",null,rowBounds);

        for(User user:users){
            System.out.println(user);
        }
        sqlSession.close();
    }
}



