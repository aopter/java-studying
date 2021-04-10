package com.lkl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 刘凯丽
 * @createTime 2021/1/29 18:01
 * @projectName mybatis-study
 * @className MybatisUtils.java
 * @description 工具类
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
//    1.获取sqlSessionFactory
    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//  我们可以从SqlSessionFactory中获得 SqlSession 的实例。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
