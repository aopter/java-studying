import com.kai.mapper.UserMapper;
import com.kai.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/8 14:51
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {
    public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserMapper mapper = context.getBean("mapper", UserMapper.class);
        List<User> users = mapper.selectUser();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
