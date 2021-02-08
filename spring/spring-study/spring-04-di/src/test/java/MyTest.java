import com.kai.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 16:52
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    @Test
    public void test01(){
        //
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
        People people = (People) context.getBean("people");
        people.getCat().shout();
        people.getDog().shout();

    }
}
