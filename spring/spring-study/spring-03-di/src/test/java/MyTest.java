import com.kai.pojo.Student;
import com.kai.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @createTime 2021/2/5 17:41
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

//测试生成对象
    @Test
    public void test01(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Student student = (Student) context.getBean("student");
//        System.out.println(student);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user);
    }
}
