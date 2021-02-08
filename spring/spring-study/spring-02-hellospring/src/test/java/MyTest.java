import com.kai.pojo.Hello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 刘凯丽
 * @createTime 2021/2/4 17:07
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    @Test
    public void test01(){
//        获取context上下文
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
//        我们的对象都在spring中管理了 我们需要用 直接抽取
        Hello str = (Hello) context.getBean("hello");

        System.out.println(str.toString());


    }
}
