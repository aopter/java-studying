import com.kai.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 17:44
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        User user = (User) context.getBean("user");

        System.out.println(user.name);
    }
}
