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

    @Test
    public void testGetUserById() {
//        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
//        输出结果
        System.out.println(user);
//        关闭
        sqlSession.close();
    }


    @Test
    public void addUser() {
//        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.addUser(new User(5,"哈哈哈","123456"));
//        输出结果
        if(result>0){
            sqlSession.commit();
            System.out.println("插入成功");
        }

//        关闭
        sqlSession.close();
    }


    @Test
    public void updateUser() {
//        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.updateUser(new User(5,"张三","123456"));
//        输出结果
        if(result>0){
            sqlSession.commit();
            System.out.println("修改成功");
        }

//        关闭
        sqlSession.close();
    }


    @Test
    public void deleteUserById() {
//        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.deleteUserById(5);
//        输出结果
        if(result>0){
            sqlSession.commit();
            System.out.println("删除成功");
        }

//        关闭
        sqlSession.close();
    }


    @Test
    public void getUserByName(){
        //        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//      输出结果
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id","1");
        map.put("userName","张三");

        User user = mapper.getUserByName(map);
        System.out.println(user);
//        关闭
        sqlSession.close();

    }
    @Test
    public void getUsersByLike(){
        //        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//      输出结果

        List<User> users = mapper.getUsersByLike("李");
        for(User user:users){
            System.out.println(user);
        }


//        关闭
        sqlSession.close();

    }

}



