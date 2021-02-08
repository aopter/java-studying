import com.kai.dao.UserMapper;
import com.kai.pojo.User;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 10:34
 * @projectName mybatis-study
 * @className UserMapperTest.java
 * @description TODO
 */
public class UserMapperTest {

    @Test
    public void TestUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int i = mapper.deleteUser(7);



        sqlSession.close();
    }

//        int toto = mapper.updateUser(new User(7, "toto", "44444"));


//        int hello = mapper.addUser(new User(7, "hello", "555555"));


//        List<User> user = mapper.getUserById(2);
//        System.out.println(user);



//        List<User> users = mapper.getUsers();
//        for(User user :users){
//            System.out.println(user);
//        }
}
