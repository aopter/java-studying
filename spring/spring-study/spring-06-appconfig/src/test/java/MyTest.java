import com.kai.pojo.User;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 18:07
 * @projectName spring-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    @Test
    public void test01(){
//完全使用了配置类方法，我们可以通过AnnotationConfigApplicationContext 获取上下文环境
        ApplicationContext context=
                new AnnotationConfigApplicationContext(com.kai.config.MyConfig.class);

        User user = (User) context.getBean("user");
        System.out.println(user);

    }
}
