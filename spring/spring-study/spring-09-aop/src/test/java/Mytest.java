import com.kai.service.UserService;
import com.kai.service.UserserviceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 16:17
 * @projectName spring-study
 * @className Mytest.java
 * @description TODO
 */
public class Mytest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
//        动态代理代理的是接口 ：注意点 所以要用接口来接收
        UserService userserviceImpl = context.getBean("userserviceImpl", UserService.class);
        userserviceImpl.add();
    }
}
