
import com.kai.mapper.UserMapper;
import com.kai.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.ibatis.io.Resources.*;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 18:09
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream in = getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.selectUser();

        for (User user : users) {
            System.out.println(user);
        }


    }


    @Test
    public void testSpringMybatis(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserMapper mapperImpl = context.getBean("userMapperImpl", UserMapper.class);
        List<User> users = mapperImpl.selectUser();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test //方式二 直接继承SqlSessionDaoSupport
    public void testSpringMybatis2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserMapper mapperImpl = context.getBean("usermapper2", UserMapper.class);
        List<User> users = mapperImpl.selectUser();
        for (User user : users) {
            System.out.println(user);
        }

    }

}
