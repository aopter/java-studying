# 1. Spring框架

## 1.1 简介

- Spring框架是由于软件开发的复杂性而创建的。Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。

- Spring是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架。
- **Rod Johnson**创建，令人吃惊的是在回到软件开发领域之前，他还获得了音乐学的博士学位。

- spring理念：使现有的理论更加容易使用，本身是一个大杂烩，融合了很多技术。





- SSH：Struct2+spring+Hibernate;
- SSM:  springMVC+spring+mybatis



官网：https://docs.spring.io/spring-framework/docs/current/reference/html/

下载地址：https://repo.spring.io/libs-release-local/org/springframework/spring/

github：https://github.com/spring-projects/spring-framework

~~~xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.3</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0</version>
</dependency>

~~~

## 1.2 优点

- Spring是一个开源的免费的框架（容器）！
- Spring是一个轻量级、非入侵式的框架！
- 控制反转（IOC），面向切面编程（AOP）
- 支持事务的处理，对框架整合的支持。



## 1.3 组成

![image-20210204160323074](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210204160323074.png)



## 1.4扩展

现代化的java开发！基于spring的开发

![image-20210204160419028](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210204160419028.png)

构建一切-->协调一切-->连接一切



- spring Boot
  - 快速开发的脚手架
  - 基于springboot可以快速的开发单个微服务
  - 约定大于配置
- spring Cloud
  - SpringCloud是基于SpringBoot实现的



# 2. IOC理论推导

原来开发流程：

UserDao接口

接口实现类Impl

UserService接口

接口实现类Impl



我们使用一个set接口接口实现，已经发生了改变

例如当dao层用不同的数据库来实现的时候

~~~java
private User userDao
    
public void setUserDao(UserDao userDao){
    this.userDao = userDao
}
~~~



- 之前，是程序主动创建对象，控制权在程序员手上
- 而现在，程序不在具有主动型，而是被动的接收



这种思想，从本质上解决了问题，我们程序猿不需要再管理创建对象，而是专注于业务的实现。

![image-20210204164833943](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210204164833943.png)



**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式，在spring中实现控制反转的是IOC容器，其实现方式是依赖注入（DI）**



# 3. HelloSpring

1.编写实体类

2.编写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">


    <!--    使用spring来创建我们的对象-->
    <bean id="hello" class="com.kai.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>

</beans>
```

3.测试

```java
    public void test01(){
        
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
//        我们的对象都在spring中管理了 我们需要用 直接抽取
        Hello str = (Hello) context.getBean("hello");

        System.out.println(str.toString());


    }
```





# 4. IOC获取对象的方式

- 默认无参构造

- 可以有参构造

  - 下标赋值

     索引从0开始。

  - 通过类型赋值  不建议

    ~~~xml
    <bean id="exampleBean" class="examples.ExampleBean">
        <constructor-arg type="int" value="7500000"/>
        <constructor-arg type="java.lang.String" value="42"/>
    </bean>
    ~~~

  - 直接通过参数名

    ~~~xml
    <bean id="exampleBean" class="examples.ExampleBean">
        <constructor-arg name="years" value="7500000"/>
        <constructor-arg name="ultimateAnswer" value="42"/>
    </bean>
    ~~~

    

在加载配置文件的时候，就已经将对象实例化了

# 5. Spring配置

### 5.1 别名

### 5.2 Bean

> ```xml-dtd
> <!--    id：Bean的唯一标识符，对象名称-->
> <!--    class：类的全限定名-->
> <!--    name:别名，而且可以同时取多个别名,分隔符可以有很多【, ;】-->
> ```

### 5.3 import

主要用于团队开发，可以有多个配置文件，将多个配置文件合并。

```java
<import resource="beans2.xml"/>
```

最后使用的时候，直接加载总的配置文件即可。



# 6. 依赖注入

依赖注入

- 依赖：bean的创建，依赖容器创建
- 注入：Bean的属性，由容器注入

## 6.1 构造器注入

参考之前

## 6.2 set注入

类型：

基本类型、引用类型、数组、集合、map、set、properties、null



**【完整代码展示】**

- 实体类

  Address

  ```java
  package com.kai.pojo;
  
  import lombok.Data;
  
  /**
   * @createTime 2021/2/5 17:33
   * @projectName spring-study
   * @className Address.java
   * @description TODO
   */
  @Data
  public class Address {
      private String address;
  }
  ```

  Student

  ```java
  package com.kai.pojo;
  
  import lombok.Data;
  
  import java.util.List;
  import java.util.Map;
  import java.util.Properties;
  import java.util.Set;
  
  /**
   * @createTime 2021/2/5 17:33
   * @projectName spring-study
   * @className Student.java
   * @description TODO
   */
  @Data
  public class Student {
  
      private String name;
      private Address address;
      private String[] books;
      private List<String> hobby;
      private Map<String,String> card;
      private Set<String> games;
      private String wife;
      private Properties info;
  
  
  }
  ```

- beans.xml

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
  
      <bean id="address" class="com.kai.pojo.Address">
          <property name="address" value="王者峡谷"/>
      </bean>
      <bean id="student" class="com.kai.pojo.Student">
          <!--        普通值注入  使用value-->
          <property name="name" value="carry"/>
          <!--        Bean注入  使用ref-->
          <property name="address" ref="address"/>
          <!--        数组注入  使用array-->
          <property name="books">
              <array>
                  <value>红楼梦</value>
                  <value>水浒传</value>
                  <value>三国演义</value>
                  <value>西游记</value>
              </array>
          </property>
          <!--        list注入  使用list-->
          <property name="hobby">
              <list>
                  <value>听歌</value>
                  <value>看电视</value>
                  <value>吃饭</value>
              </list>
          </property>
          <!--        map注入  使用map-->
          <property name="card">
              <map>
                  <entry key="身份证" value="121212"/>
                  <entry key="卡" value="45454"/>
              </map>
          </property>
          <!--        set注入  使用set-->
          <property name="games">
              <set>
                  <value>LOL</value>
              </set>
          </property>
          <!--        null注入  使用null-->
          <property name="wife">
              <null/>
          </property>
          <!--        Propties注入  -->
          <property name="info">
              <props>
                  <prop key="学号">2015444</prop>
                  <prop key="性别">女</prop>
              </props>
  
          </property>
      </bean>
  </beans>
  ```

- 测试类

  ```java
  import com.kai.pojo.Student;
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
          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          Student student = (Student) context.getBean("student");
          System.out.println(student);
      }
  }
  ```



## 6.3 拓展方式注入

需要在配置文件中写入约束：

```xml
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
```

p命名空间注入，直接注入属性的值-------set注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    p命名空间注入 直接注入属性的值-->
    <bean id="user" class="com.kai.pojo.User" p:age="14" p:name="carry"/>

</beans>
```

c命名空间注入，必须有有参构造--------构造器注入



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


<!--    c命名空间注入，有参构造注入-->
    <bean id="user2" class="com.kai.pojo.User" c:age="20" c:name="lary"/>

</beans>


```

# 7. Bean的作用域

## 7.1 概览

![image-20210205182701780](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210205182701780.png)

## 7.2 单例模式

默认为单例模式

- 显式设为单例

```xml
<bean id="user2" class="com.kai.pojo.User" c:age="20" c:name="lary" scope="singleton"/>
```

## 7.3 原型模式

> 每次从容器中获取对象，都会生成对象

```xml
<bean id="user2" class="com.kai.pojo.User" c:age="20" c:name="lary" scope="prototype"/>
```

## 7.4 其余作用域

只能在web开发中使用。

# 8. Bean自动装配

spring有三种装备方式

- xml装配
- java代码中装配
- 自动装配



## 8.1 ByName注入



> 会自动在容器上下文查找，和自己对象set方法后面的值对应的 beanid。

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="cat" class="com.kai.pojo.Cat"/>
    <bean id="dog" class="com.kai.pojo.Dog"/>
<!--    byName自动装配-->
    <bean id="people" class="com.kai.pojo.People" autowire="byName">
        <property name="name" value="carry"/>
    </bean>
</beans>
```

## 8.2 ByType注入

> 会自动在容器上下文查找，和自己对象属性类型相同的bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="cat" class="com.kai.pojo.Cat"/>
    <bean id="dog" class="com.kai.pojo.Dog"/>
<!--    byType自动装配-->
    <bean id="people" class="com.kai.pojo.People" autowire="byType">
        <property name="name" value="carry"/>
    </bean>
</beans>
```



**【注意】**

- byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法
- bytype的时候，需要保证所有的bean的class一直，并且这个bean需要和自动注入的属性的类型一致

## 8.3 使用注解装配

jdk1.5支持注解

spring2.5支持注解

**使用注解需要**：



1.导入约束：context约束

2.配置注解的支持

```xml
<context:annotation-config/>
```

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
~~~

3.实体类

### @Autowired

 **默认按类型装配**

如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。

```java
public class TestServiceImpl {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao; 
}
```



在属性/set方法上使用注解

```java
package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @createTime 2021/2/6 16:47
 * @projectName spring-study
 * @className People.java
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;

}
```



注意：

```
//    如果显示的说明了 required = false ，表明该属性为空也不会报错
    @Autowired(required = false)
    private Cat cat;
```



### @Resource 

**默认按照名字装配**

```java
public class People {
    @Resource
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;

}
```

@Resource

- 是java注解，而不是spring

- 默认使用byName
- name不符合的话，也会根据type



# ９. 使用注解开发



> 在spring4之后，要使用注解开发，一定要导入aop的包

> 使用注解需要导入context约束

1. bean

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <!--    支持注解-->
       <context:annotation-config/>
       <!--指定要扫描的包-->
       <context:component-scan base-package="com"/>
   </beans>
   ```

2. 属性如何注入

   ```java
   @Component
   public class User {
       @Value("carry")
       public String name;
   }
   ```

3. 衍生的注解

   @Component有好多衍注解，比如我们在web架构中用到mvc三层架构

   @Repository -----------dao层

   @service -----------service层

   @controller-----------controller层

   - 功能一样，都是代表将我们的某个类注册到spring容器中装配
   - 只是作为层的区分

4. 自动装配

   见8.3

5. 作用域

   ```java
   @Component
   @Scope("prototype")
   public class User {
       @Value("carry")
       public String name;
   }
   ```

   

6. 小结

# 10. 使用java配置spring

![image-20210206180935259](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210206180935259.png)

1. 配置类

   ```java
   //该类也会被spring容器托管，因为他本质上也是一个组件
   //该类是一个配置类，相当于applicaitoncontext.xml
   @Configuration
   @ComponentScan("com.kai")//扫描
   public class MyConfig {
   
   //  注册了一个bean
   //  方法的名字就是配置文件中bean标签 的 id属性
   //      返回值就是配置文件中bean标签 的 class属性
       @Bean
       public User user(){
           return new User();
       }
   }
   ```

2. bean

   ```java
   @Component
   public class User {
       @Value("carry")
       private String name;
   }
   ```

3. 测试

   ```java
       @Test
       public void test01(){
   //完全使用了配置类方法，我们可以通过AnnotationConfigApplicationContext 获取上下文环境
           ApplicationContext context=
                   new AnnotationConfigApplicationContext(com.kai.config.MyConfig.class);
   
           User user = (User) context.getBean("user");
           System.out.println(user);
   
       }
   ```



这种注解配置中：springboot中随处可见。

# 11. 代理模式

代理模式的分类：

- 静态代理
- 动态代理

## 11.1 静态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户角色：访问代理对象的人



代理模式的好处：

- 可以使真实角色更加纯粹，不去关注一些公共的业务。
- 公共业务就交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理

缺点：

- 一个真实角色就会产生一个代理角色；代码量会翻倍，

 

代码步骤：

1. 接口
2. 真实角色
3. 代理角色
4. 客户端访问代理角色



## 11.2 动态代理

- 底层都是反射

- 动态代理和静态代理角色一样

- 动态代理的代理类是动态生成的，不是我们直接写好的

- 动态代理分为两大类：基于接口的动态代理、基于类的动态代理

  - 基于接口-------JDK动态代理【用】
  - 基于类-------cglib
  - java字节码实现----javasist

两个类：Proxy：代理，InvocationHandler：调用处理程序

动态代理工具类

```java
public class ProxyInvocationHandler implements InvocationHandler {

//    被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //    获得代理实例
    public Object getProxy(){
        return  Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

//   处理代理市里，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("执行方法前");
//        动态代理的本质
        Object result = method.invoke(target, args);
        System.out.println("执行方法后");

        return result;
    }
}
```



测试类

```java
public class Client {
    public static void main(String[] args) {
//真实角色
        UserServiceImpl userService = new UserServiceImpl();
//       代理角色：不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(userService);
//        动态生成代理类
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
//      执行方法
        proxy.add();

    }
}
```



# 12. AOP

## 12.1AOP基础

### 12.1.1 什么是AOP

> AOP全称`Aspect Oriented Programming`意为面向切面编程，也叫做面向方法编程，是通过预编译方式和运行期动态代理的方式实现不修改源代码的情况下给程序动态统一添加功能的技术。

- 利用AOP可以对业务逻辑各个部分进行隔离，从而使业务逻辑各部分之间的耦合度降低
- 提高程序的可重用性，同时提高开发效率。
- AOP的使用场景主要包括日志记录、性能统计、安全控制、事务处理、异常处理等。



### 12.1.2 AOP运行原理

![image-20210207123435741](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210207123435741.png)

### 12.1.3 AOP 术语

在我们开始使用 AOP 工作之前，让我们熟悉一下 AOP 概念和术语。这些术语并不特定于 Spring，而是与 AOP 有关的。



| 项            | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| Aspect        | 一个模块具有一组提供横切需求的 APIs。例如，一个日志模块为了记录日志将被 AOP 方面调用。应用程序可以拥有任意数量的方面，这取决于需求。 |
| Join point    | 在你的应用程序中它代表一个点，你可以在插件 AOP 方面。你也能说，它是在实际的应用程序中，其中一个操作将使用 Spring AOP 框架。 |
| Advice        | 这是实际行动之前或之后执行的方法。这是在程序执行期间通过 Spring AOP 框架实际被调用的代码。 |
| Pointcut      | 这是一组一个或多个连接点，通知应该被执行。你可以使用表达式或模式指定切入点正如我们将在 AOP 的例子中看到的。 |
| Introduction  | 引用允许你添加新方法或属性到现有的类中。                     |
| Target object | 被一个或者多个方面所通知的对象，这个对象永远是一个被代理对象。也称为被通知对象。 |
| Weaving       | Weaving 把方面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时，类加载时和运行时完成。 |

### 12.1.4通知的类型

Spring 方面可以使用下面提到的五种通知工作：

| 通知           | 描述                                                         |
| -------------- | ------------------------------------------------------------ |
| 前置通知       | 在一个方法执行 之前，执行通知。                              |
| 后置通知       | 在一个方法执行 之后，不考虑其结果，执行通知。                |
| 返回后通知     | 在一个方法执行 之后，只有在方法成功完成时，才能执行通知。    |
| 抛出异常后通知 | 在一个方法执行 之后，只有在方法退出抛出异常时，才能执行通知。 |
| 环绕通知       | 在建议方法调用之前和之后，执行通知。                         |



## 12.2代码实现

导入包：

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```

### 方式一：使用Spring的API接口

类：

```java
@Component
public class Log implements MethodBeforeAdvice {

//    method：要执行的目标对象的方法
//    args:参数
//    target：目标对象
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"的"+method.getName()+"方法执行了");
    }
}
```

```java
@Component
public class AfterLog implements AfterReturningAdvice {
//    returnValue返回值
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了"+method.getName()+"  返回结果为:"+returnValue);
    }
}
```

配置文件

```xml
<!--    方式一：使用原生springAPI接口-->
<!--    配置aop:导入aop的约束-->
    <aop:config>
<!--        切入点  expression表达式 execution(修饰词 返回值 类名 方法名 参数)-->
        <aop:pointcut id="pointcut" expression="execution(* com.kai.service.UserserviceImpl.*(..))"/>
<!--    执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>

    </aop:config>
```



### 方式二：自定义类实现AOP

切面类：

```java
@Component
public class MyPointCut {

    public void before(){
        System.out.println("========执行方法前");
    }
    public void after(){
        System.out.println("========执行方法后");
    }
}
```



配置文件

```xml
<!--    方式二：自定义类-->
    <aop:config>
<!--        自定义切面 ref：要引用的类-->
        <aop:aspect ref="myPointCut">
<!--        切入点-->
            <aop:pointcut id="point" expression="execution(* com.kai.service.UserserviceImpl.*(..))"/>
<!--        通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
```

### 方式三：注解方式实现

```xml
<!--    方式三    JDK(默认) 参数：proxy-target-class为false true：chlib-->
    <aop:aspectj-autoproxy proxy-target-class="false"/>
```



```java
package com.kai.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @createTime 2021/2/7 16:39
 * @projectName spring-study
 * @className AnonotationPointCut.java
 * @description TODO
 */

@Component//需要注册类
@Aspect//表明这个类是一个切面
public class AnonotationPointCut {

    @Before("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void before(){
        System.out.println("========执行方法前");
    }

    @After("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void after(){
        System.out.println("========执行方法后");
    }

//    在环绕增强中 我们可以给定一个参数 代表我们要获取切入的点
    @Around("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前");
//        获得签名的方法
        Signature signature = joinPoint.getSignature();
        System.out.println("签名："+signature);
//        执行方法
        joinPoint.proceed();//方法执行
        System.out.println("环绕后");
    }
}
```





# 13. 整合Mybatis



## 13.1 方式一

步骤：

1. 导入jar包

   - junit
   - mybatis
   - mysql
   - spring相关包
   - aop织入包
   - spring-jdbc
   - mybatis-spring（新包）

   ```xml
   <dependencies>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.13</version>
       </dependency>
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.17</version>
       </dependency>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.3.3</version>
       </dependency>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.3.3</version>
       </dependency>
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.9.4</version>
       </dependency>
   
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.2</version>
       </dependency>
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
   
       </dependency>
   </dependencies>
   ```

**数据源dataSource**

**sqlSessionFactory**

**SqlSessionTemplate**

- `SqlSessionTemplate` 是 MyBatis-Spring 的核心。作为 `SqlSession` 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 
- `SqlSession``SqlSessionTemplate` 是线程安全的，可以被多个 DAO 或映射器所共享使用。

- 可以使用 `SqlSessionFactory` 作为构造方法的参数来创建 `SqlSessionTemplate` 对象。

```xml
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
  <constructor-arg index="0" ref="sqlSessionFactory" />
</bean>
```

2.编写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <!--    DataSource:使用spring的数据源替换mybatiis的配置
    数据源有：c3p0 dbcp等等 这里我们使用spring提供的jdbc-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatistest?userSSL=true&amp;useUnicode=true&amp;characterEncoding=utf-8"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
    </bean>
    <!--   sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!--        可以做mybatis一些配置，同时可以绑定外部的mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/kai/mapper/UserMapper.xml"/>
    </bean>

    <!--SqlSessionTemplate:就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能用构造器注入sqlSessionFactory，因为它没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>


</beans>
```



3.编写mapper的一个实现类

```java
    @Repository
public class UserMapperImpl implements UserMapper{
    private SqlSessionTemplate sqlSession;
    @Autowired//注入
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```



3.测试

```java
@Test
public void testSpringMybatis(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
    UserMapper mapperImpl = context.getBean("userMapperImpl", UserMapper.class);
    List<User> users = mapperImpl.selectUser();
    for (User user : users) {
        System.out.println(user);
    }

}
```



## 13.2 方式二

- 不需要注册**SqlSessionTemplate**

- 需要让mapper实现类继承SqlSessionDaoSupport

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{


    public List<User> selectUser() {

        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

- 并且在配置为改mapper实现类 注入一个sqlSessionFactory



# 14. 事务管理

两种事务管理方式：

- 声明式事务（交由容器管理事务）
- 编程式事务（需要修改代码，比如trycatch）

![image-20210208143839366](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210208143839366.png)



### 14.2 Spring中七种Propagation类的事务属性详解：



 **REQUIRED**：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 

 **SUPPORTS**：支持当前事务，如果当前没有事务，就以非事务方式执行。 

 **MANDATORY**：支持当前事务，如果当前没有事务，就抛出异常。 

 **REQUIRES_NEW**：新建事务，如果当前存在事务，把当前事务挂起。 

 **NOT_SUPPORTED**：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 

 **NEVER**：以非事务方式执行，如果当前存在事务，则抛出异常。 

 **NESTED**：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务。