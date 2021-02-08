



# 1.简介

## 1.1什么是MyBatis

![image-20210129164706394](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210129164706394.png)

- MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。
- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

- MyBatis 本是apache的一个[开源项目](https://baike.baidu.com/item/开源项目/3406069)iBatis, 2010年这个[项目](https://baike.baidu.com/item/项目/477803)由apache software foundation 迁移到了[google code](https://baike.baidu.com/item/google code/2346604)，并且改名为MyBatis 。
- 2013年11月迁移到[Github](https://baike.baidu.com/item/Github/10145341)。

获取路径

- maven仓库：

  ~~~java
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.2</version>
  </dependency>
  
  ~~~

  

- github：https://github.com/mybatis/mybatis-3/releases

- 中文文档：https://mybatis.org/mybatis-3/zh/index.html



## 1.2持久化

数据持久化

- 持久化就是讲程序在持久状态和瞬时状态转化的过程
- 方式：数据库(jdbc)，io文件持久化

为什么要持久化

- 内存：**断电即失**



## 1.3Mybatis优点

- 传统的jdbc太复杂了，简化框架、自动化。

- 简单易学
- 灵活
- 解除sql与程序代码的耦合
- 提供映射标签，支持对象与数据库的orm字段关系映射
- 提供对象关系映射标签，支持对象关系组建维护
- 提供xml标签，支持编写动态sql。



# 2.第一个MyBatis程序

思路：搭建环境--->导入MyBatis-->编写代码--->测试

## 2.1配置环境

- 创建数据库

- 导入父工程依赖

- ~~~java
  <dependencies>
  <!--    mysql-->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.17</version>
      </dependency>
      <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.2</version>
      </dependency>
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
  </dependencies>
  
  ~~~

- 创建子工程

- 编写mybatis配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
  <!--核心配置文件-->
  <configuration>
  <!--    几套环境-->
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatistest?userSSL=true&amp;useUnicode=true&amp;characterEncoding=utf-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value=""/>
              </dataSource>
          </environment>
      </environments>
      <mappers>
          <mapper resource="org/mybatis/example/BlogMapper.xml"/>
      </mappers>
  </configuration>
  ```

- 编写mybatis工具类

  ```java
  package com.kai.util;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  /**
   * @createTime 2021/1/29 18:01
   * @projectName mybatis-study
   * @className MybatisUtils.java
   * @description 工具类
   */
  public class MybatisUtils {
  
      private static SqlSessionFactory sqlSessionFactory;
  //    1.获取sqlSessionFactory
      static {
          String resource = "mybatis-config.xml";
          try {
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  //    既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
  //    SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
  //    你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
      public static SqlSession getSqlSession(){
          return sqlSessionFactory.openSession();
      }
  }
  ```

## 2.2编写代码

- 实体类

  ```java
  package com.kai.pojo;
  
  /**
   * @createTime 2021/1/29 18:10
   * @projectName mybatis-study
   * @className User.java
   * @description TODO
   */
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  ```

- Dao接口

  ```java
  package com.kai.dao;
  
  import com.kai.pojo.User;
  
  import java.util.List;
  
  /**
   * @createTime 2021/1/29 18:18
   * @projectName mybatis-study
   * @className UserDao.java
   * @description TODO
   */
  public interface UserDao {
      List<User> getUsers();
  }
  ```

- 接口实现类  变为配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.kai.dao.UserDao">
      <select id="getUsers" resultType="com.kai.pojo.User">
      select * from mybatistest.user
    </select>
  </mapper>
  ```



## 2.3测试

```java
package com.kai.dao;

import com.kai.pojo.User;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @createTime 2021/1/29 18:43
 * @projectName mybatis-study
 * @className userDaoTest.java
 * @description TODO
 */
public class userDaoTest {

    @Test
    public void test(){
//        1.获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        2.执行sql  方式一：getmapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> users = mapper.getUsers();
//      遍历
        for(User user:users){
            System.out.println(user);
        }
//        关闭
        sqlSession.close();
    }
//    获取sqlsession
}
```

注意

1. 资源过滤问题

   ![image-20210129191544272](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210129191544272.png)

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
        </resource>

        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
        </resource>
    </resources>
</build>
```



2.配置文件没有注册

3.方法名不对

4.返回类型不对

# 3.CRUD

## 3.1通用基本步骤

1. 编写mapper接口方法
2. 在配置文件中注册方法写出sql语句
3. 测试
   - 获取SqlSession对象来执行sql语句
   - 获取mapper的实例化对象
   - 用对象执行具体方法
   - 增删改 需要提交事务！！
   - 关闭sql

## 3.2 select

1. 编写mapper接口方法

   ```java
   package com.kai.dao;
   
   import com.kai.pojo.User;
   
   import java.util.List;
   
   /**
    * @createTime 2021/1/29 18:18
    * @projectName mybatis-study
    * @className UserDao.java
    * @description TODO
    */
   public interface UserMapper {
       List<User> getUsers();
   //  根据id查询
       User getUserById(int id);
   //  添加
       int addUser(User user);
   //  更新
       int updateUser(User user);
   //   删除
       int deleteUserById(int id);
   }
   ```

   

2. 在配置文件中注册方法写出sql语句

   ```xml
   <select id="getUsers" resultType="com.kai.pojo.User">
   select * from mybatistest.user
   </select>
   <select id="getUserById" resultType="com.kai.pojo.User" parameterType="int">
       select * from mybatistest.user where id = #{id}
   </select>
   ```

3. 测试

   ```java
       @Test
       public void testGetUserById() {
   //        获取
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   //        执行sql
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           User user = mapper.getUserById(1);
   //        输出结果
           System.out.println(user);
   //        关闭
           sqlSession.close();
       }
   ```

## 3.3 delete

1. 编写mapper接口方法

2. 在配置文件中注册方法写出sql语句

   ~~~java
   <delete id="deleteUserById" parameterType="int">
       delete from mybatistest.user where id = #{id}
   </delete>
   ~~~

   

3. 测试

   ```java
       public void deleteUserById() {
   //        获取
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   //        执行sql
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           int result = mapper.deleteUserById(5);
   //        输出结果
           if(result>0){
               sqlSession.commit();
               System.out.println("删除成功");
           }
   
   //        关闭
           sqlSession.close();
       }
   }
   ```

## 3.4 update

1. 编写mapper接口方法

2. 在配置文件中注册方法写出sql语句

   ~~~java
       <update id="updateUser" parameterType="com.kai.pojo.User">
           update mybatistest.user set name=#{name},pwd=#{pwd}  where id =#{id};
       </update>
   ~~~

   

3. 测试

   ```java
   public void updateUser() {
   //        获取
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   //        执行sql
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           int result = mapper.updateUser(new User(5,"嘿嘿嘿","123456"));
   //        输出结果
           if(result>0){
               sqlSession.commit();
               System.out.println("修改成功");
           }
   
   //        关闭
           sqlSession.close();
       }
   ```

## 3.5 insert

1. 编写mapper接口方法

2. 在配置文件中注册方法写出sql语句

   ```xml
   <insert id="addUser" parameterType="com.kai.pojo.User">
       insert into mybatistest.user (id,name,pwd) value (#{id},#{name},#{pwd})
   </insert>
   ```

3. 测试

```java
   public void addUser() {
//        获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.addUser(new User(5,"哈哈哈","123456"));
//        输出结果
        if(result>0){
            sqlSession.commit();
            System.out.println("插入成功");
        }

//        关闭
        sqlSession.close();
    }
```



## 3.6万能Map

**这是在项目中常常用到的方式**

加入一个实体类有很多的属性，只有部分属性需要修改，可以使用万能map的方式。

1. 编写mapper接口方法

   ```java
   User getUserByName(Map<String,Object> map);
   ```

2. 在配置文件中注册方法写出sql语句

   ```xml
   <select id="getUserByName" resultType="com.kai.pojo.User" parameterType="map">
       select * from mybatistest.user where name = #{userName} and id = #{id}
   </select>
   ```

3. 测试

   ```java
       public void getUserByName(){
           //        获取
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   //        执行sql
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   //      输出结果
           Map<String, Object> map = new HashMap<String, Object>();
           map.put("id","1");
           map.put("userName","张三");
   
           User user = mapper.getUserByName(map);
           System.out.println(user);
   //        关闭
           sqlSession.close();
   
       }
   ```



## 3.7模糊查询

- 在sql语句中拼出

- 在java代码中使用通配符

  ~~~sql
  select * from mybatistest.user where name like “%”#{name}“%”
  ~~~

  

# 4.配置

## 4.1核心配置文件

```xml
<?xml version="1.0" encoding="UTF8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!--核心配置文件-->
<configuration>
<!--    几套环境-->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatistest?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT"/>
                <property name="username" value="root"/>
                <property name="password" value=""/>
            </dataSource>
        </environment>
    </environments>
<!--    每个mapper都需要在配置文件中注册-->
    <mappers>
        <mapper resource="com/kai/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```



MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：

~~~xml
properties（属性）
settings（设置）
typeAliases（类型别名）
typeHandlers（类型处理器）
objectFactory（对象工厂）
plugins（插件）
environments（环境配置）
environment（环境变量）
transactionManager（事务管理器）
dataSource（数据源）
databaseIdProvider（数据库厂商标识）
mappers（映射器）
~~~

## 4.2配置

MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中， 现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置。

**尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

**每个数据库对应一个 SqlSessionFactory 实例**

~~~xml
默认使用的环境 ID（比如：default="development"）。
每个 environment 元素定义的环境 ID（比如：id="development"）。
事务管理器的配置（比如：type="JDBC"）。
数据源的配置（比如：type="POOLED"）。
~~~

### 事务管理器

在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"）：

- JDBC – 这个配置直接使用了 JDBC 的提交和回滚设施，它依赖从数据源获得的连接来管理事务作用域。
- MANAGED – 这个配置几乎没做什么。它从不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。

### 数据源

dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。

有三种内建的数据源类型（也就是 type="[UNPOOLED|POOLED|JNDI]"）

## 4.3属性（properties）

这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置。**【如果有相同属性，优先级：外部配置文件>peoperties子元素】**

1. 新建.properties文件

   ```properties
   driver=com.mysql.jdbc.Driver
   url=jdbc:mysql://localhost:3306/mybatistest?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
   username=root
   password=
   ```

2. 在核心配置文件中引入.properties文件**<u>【注意配置文件的标签是有顺序的】</u>**

   ```xml
   <properties resource="org/mybatis/example/config.properties">
     <property name="username" value="dev_user"/>
     <property name="password" value="F2Fa3!33TYyg"/>
   </properties>
   ```

3. 在核心配置文件中  替换需要动态配置的属性值。比如:

   ```xml
   <dataSource type="POOLED">
     <property name="driver" value="${driver}"/>
     <property name="url" value="${url}"/>
     <property name="username" value="${username}"/>
     <property name="password" value="${password}"/>
   </dataSource>
   ```

## 4.4类型别名（typeAliases）

- 类型别名可为 Java 类型设置一个缩写名字。

- 意在降低冗余的全限定类名书写。

  两种方式

  ​	1.为每一个类设置别名

  ```xml
  <typeAliases>
    <typeAlias alias="Author" type="domain.blog.Author"/>
  </typeAliases>
  ```

  ​	2.也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean

  【每一个在包 `domain.blog` 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名。 比如 `domain.blog.Author` 的别名为 `author`；若有注解，<u>则别名为其注解值</u>】

  ```xml
  <typeAliases>
    <package name="domain.blog"/>
  </typeAliases>
  ```



常见的 Java 类型内建的类型别名。它们都是不区分大小写的，注意，为了应对原始类型的命名重复，采取了特殊的命名风格。

| 别名       | 映射的类型 |
| :--------- | :--------- |
| _byte      | byte       |
| _long      | long       |
| _short     | short      |
| _int       | int        |
| _integer   | int        |
| _double    | double     |
| _float     | float      |
| _boolean   | boolean    |
| string     | String     |
| byte       | Byte       |
| long       | Long       |
| short      | Short      |
| int        | Integer    |
| integer    | Integer    |
| double     | Double     |
| float      | Float      |
| boolean    | Boolean    |
| date       | Date       |
| decimal    | BigDecimal |
| bigdecimal | BigDecimal |
| object     | Object     |
| map        | Map        |
| hashmap    | HashMap    |
| list       | List       |
| arraylist  | ArrayList  |
| collection | Collection |
| iterator   | Iterator   |

## 4.5Mapper映射

方式一：resource

```xml
<!--    每个mapper都需要在配置文件中注册-->
    <mappers>
        <mapper resource="com/kai/dao/UserMapper.xml"/>
    </mappers>
```



方式二：class

```xml
<mappers>
    <mapper class="com.kai.dao.UserMapper"/>
</mappers>
```

方式三：packege

```xml
<mappers>
    <package name="com.kai.dao"/>
</mappers>
```

**<u>【注意方式二三 的应用有条件】：</u>**

- mapper文件和对应的接口同名

- mapper文件和对应的接口同包

  ![image-20210131161037398](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210131161037398.png)



## 4.6生命周期和作用域

理解我们之前讨论过的不同作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

#### SqlSessionFactoryBuilder

- 一旦创建了 SqlSessionFactory，就不再需要它了。
-  SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。

#### SqlSessionFactory

- 可以想象程：连接池

- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
- SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

#### SqlSession

- 每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。 
- 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。在Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。
-  每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它。 这个关闭操作很重要，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。

![image-20210131162631648](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210131162631648.png)

## 4.7ResultMap结果集

- 可以解决 数据库表与实体类类字段名称不一致

```xml
<resultMap id="userResultMap" type="User">
  <id property="id" column="user_id" />
  <result property="username" column="user_name"/>
  <result property="password" column="hashed_password"/>
</resultMap>
<select id="selectUsers" resultType="User">
  select id, username, hashedPassword
  from some_table
  where id = #{id}
</select>

```

​      如果这个世界总是这么简单就好了。

- 复杂关系中可以用到



# 5.日志

## 5.1日志工厂

如果一个数据库操作出现了异常，日至就是最好的助手

曾经：debug、sout

现在日志工厂

Mybatis 通过使用内置的日志工厂提供日志功能。内置日志工厂将会把日志工作委托给下面的实现之一：

- SLF4J、
- LOG4J、【掌握】
- LOG4J2、
- JDK_LOGGING、
- COMMONS_LOGGING、
- STDOUT_LOGGING、【掌握】【标准日志工厂实现】
- NO_LOGGING，

具体方式在配置文件中指定



**STDOUT_LOGGING、【掌握】【标准日志工厂实现】**

​	1.配置

```xml
<!--    日志-->
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

​	2.结果

~~~xml
Opening JDBC Connection
Created connection 949057310.
Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@3891771e]
==>  Preparing: select * from mybatistest.user 
==> Parameters: 
<==    Columns: id, name, pwd
<==        Row: 1, 张三, 123456
<==        Row: 2, 李四, 123456
<==        Row: 3, 小刘, 111111
<==        Row: 5, 张李, 123456
<==      Total: 4
User{id=1, name='张三', pwd='123456'}
User{id=2, name='李四', pwd='123456'}
User{id=3, name='小刘', pwd='111111'}
User{id=5, name='张李', pwd='123456'}
Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@3891771e]
Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@3891771e]
Returned connection 949057310 to pool.
~~~

## 5.2 LOG4J

### 什么是log4j？

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件等；
- 我们也可以控制每一条日志的输出格式
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程

### 使用log4j

1. 先导入log4j的包

   ~~~xml
   <!-- https://mvnrepository.com/artifact/log4j/log4j -->
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   
   ~~~

   

2. 配置文件

   ```properties
   log4j.rootLogger=DEBUG,console,file
   
   #控制台输出的相关设置
   log4j.appender.console = org.apache.log4j.ConsoleAppender
   log4j.appender.console.Target = System.out
   log4j.appender.console.Threshold=DEBUG
   log4j.appender.console.layout = org.apache.log4j.PatternLayout
   log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
   
   #文件输出的相关设置
   log4j.appender.file = org.apache.log4j.RollingFileAppender
   log4j.appender.file.File=./log/kuang.log
   log4j.appender.file.MaxFileSize=10mb
   log4j.appender.file.Threshold=DEBUG
   log4j.appender.file.layout=org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
   
   #日志输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```

   3.配置文件中注册：

   

### 简单使用

​		1.导入包import org.apache.log4j.Logger;

​		2.日志对象 加载参数为当前类的class

~~~java
   static Logger logger = Logger.getLogger(userDaoTest.class);
~~~



# 6.分页

### 6.1lmit分页

```sql
select * from user limit startIndex,pageSize
```

### 6.2RowBounds分页

用java代码的方式实现分页

```java
public void testRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        RowBounds rowBounds = new RowBounds(1, 2);

//      通过java代码分页
        List<User> users = sqlSession.selectList("com.kai.dao.UserMapper.getUsersByRowBounds",null,rowBounds);

        for(User user:users){
            System.out.println(user);
        }
        sqlSession.close();
    }
```

### 6.3分页插件



# 7.注解方式

## 7.1测试实现

主要原理是反射 ，底层用到了动态代理

```java
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

}
```

测试使用

```java
public void TestUser(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    List<User> users = mapper.getUsers();
    for(User user :users){
        System.out.println(user);
    }

    sqlSession.close();
}
```



## 7.2CRUD

1.编写接口

根据id查询用户 多个参数时，基本类型（加上string）的每个参数都需要加@param 引用类型不需要

```java
package com.kai.dao;

import com.kai.pojo.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @createTime 2021/1/29 18:18
 * @projectName mybatis-study
 * @className UserDao.java
 * @description TODO
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

//    根据id查询用户 多个参数时，每个参数都需要加@param 引用类型不需要
    @Select("select * from user where id = #{id}")
    List<User>  getUserById(@Param("id") int id);

//    增加
    @Insert("insert into user(id,name,pwd) value (#{id},#{name},#{pwd})")
    int addUser(User user);
//    删除
    @Delete("delete from user where id = #{id}")
    int deleteUser(@Param("id") int id);
//    更改
    @Update("update user set name=#{name},pwd=#{pwd} where id = #{id}")
    int updateUser(User user);
}
```



# 8.复杂查询

## 8.1基础概念

**关联**：多对一

**集合**：一对多

环境搭建

~~~java
CREATE TABLE `teacher` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  `tid` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
~~~

## 8.2多对一处理

### 方式一：按照查询嵌套处理（嵌套查询）

```xml
<mapper namespace="com.kai.dao.StudentMapper">

    <!--select s.id,s.name,t.name from student s,teacher t where s.tid = t.id;-->
    <!--    查询所有学生的详细信息 （子查询）-->
    <!-- 1.查询所有学生的信息-->
    <!-- 2.根据学生信息的tid 查到老师-->
    <!--   结果集映射 -->
    <select id="getStudentInfo" resultMap="StudentTeacher">
        select * from student
    </select>
    
    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="id"/>
<!--        复杂的属性需要单独处理 对象：association 集合：collection-->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacherById"/>
    </resultMap>
    <select id="getTeacherById" resultType="Teacher">
        select * from teacher where id = #{id}
    </select>
</mapper>
```



### 方式二：按照结果嵌套处理（连表查询）

```xml
<select id="getStudentInfo2" resultMap="StudentTeacher2">
    select s.id sid,s.name sname,t.name tname
    from student s,teacher t
    where s.tid = t.id
</select>
<resultMap id="StudentTeacher2" type="Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
    </association>
</resultMap>
```



## 8.3一对多处理

方式一：按照结果嵌套处理

```xml
<mapper namespace="com.kai.dao.TeacherMapper">

    <select id="getTeacherById" resultMap="TeacherStudent">
        select t.id tid,t.name tname,s.id sid,s.name sname
        from teacher t, student s
        where t.id=s.tid and t.id = #{tid}
    </select>
    <resultMap id="TeacherStudent" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
        <!--        复杂的属性需要单独处理 对象：association 集合：collection-->
        <!--javaType:指定的属性类型 集合中的泛型信息用oftype-->
        <collection property="students" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>

    </resultMap>
</mapper>
```



# 9.动态SQL

## 9.1概念

**什么是动态SQL：根据不同的条件生成不同的SQL语句**

> 如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。
>
> - if
> - choose (when, otherwise)
> - trim (where, set)
> - foreach



## 9.2搭建环境

- 数据库表

~~~sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT '博客id',
`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
`create_time` DATETIME NOT NULL COMMENT '创建时间',
`views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8
~~~



- 创建基础工程：

1. 导包

2. 编写配置文件

3. 创建实体类

   ```java
   public class Blog {
       private String id;
       private String title;
       private String author;
       private int views;
       private Date createTime;//和数据库命名不一致
   }
   ```

4. 编写实体类对应mapper和mapper.xml

5. 测试

## 9.3自动转换为驼峰命名

mybatis 设置的一项属性 mapUnderscoreToCamelCase = true

## 9.4 IF标签

```xml
<select id="queryBlogs" parameterType="map" resultType="blog">
    select * from blog
    <where>//没有元素符合 会自动删除where，第二个符合 去掉and
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
    </where>
</select>
```

*where* 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，*where* 元素也会将它们去除。



```xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```



## 9.5 choose  (when, otherwise)

> 类似于java中的 swich case语句

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG 
  <where>
  	<choose>
    	<when test="title != null">
      	AND title like #{title}
    	</when>
    	<when test="author != null and author.name != null">
      	AND author_name like #{author.name}
    	</when>
    	<otherwise>
      	AND featured = 1
    	</otherwise>
  		</choose>
  </where>
</select>
```

## 9.6 foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。比如： 

~~~xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
~~~

*foreach* 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。

它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符，看它多智能！



## 9.7 SQL片段

为了减少代码的重复，增强复用性，将重复的代码提取出来

- 将公共部分提取出来

  ~~~xml
  <sql id="if-title-author">
      <if test="title != null">
          and title = #{title}
      </if>
      <if test="author != null">
          and author = #{author}
      </if>
  </sql>
  ~~~



- 使用的时候用<include>标签

  ~~~xml
  <select id="queryBlogs" parameterType="map" resultType="blog">
      select * from blog
      <where>
          <include refid="if-title-author"/>
      </where>
  </select>
  
  ~~~

注意：

1.不要包括where

2.最好基于单表

# 10.缓存

## 10.1简介

- MyBatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率。

- MyBatis系统中默认定义了两级缓存：**一级缓存**和**二级缓存**

- - 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存



## 10.2一级缓存

一级缓存也叫本地缓存：

- 与数据库同一次会话期间查询到的数据会放在本地缓存中。
- 以后如果需要获取相同的数据，直接从缓存中拿，没必须再去查询数据库；

### 一级缓存失效的四种情况

1、sqlSession不同

2、sqlSession相同，查询条件不同

3、sqlSession相同，两次查询之间执行了增删改操作！

4、sqlSession相同，手动清除一级缓存

一级缓存就是一个map

## 10.3二级缓存

默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

```
<cache/>
```

基本上就是这样。这个简单语句的效果如下:

- 映射语句文件中的所有 select 语句的结果将会被缓存。
- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。



> 使用步骤

1、开启全局缓存 【mybatis-config.xml】

```
<setting name="cacheEnabled" value="true"/>
```

2、去每个mapper.xml中配置使用二级缓存，这个配置非常简单；【xxxMapper.xml】

```xml
<cache/>

官方示例=====>查看官方文档
<cache
 eviction="FIFO"
 flushInterval="60000"
 size="512"
 readOnly="true"/>
这个更高级的配置创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突。
```

3、代码测试

- 所有的实体类先实现序列化接口
- 测试代码

```java
@Test
public void testQueryUserById(){
   SqlSession session = MybatisUtils.getSession();
   SqlSession session2 = MybatisUtils.getSession();

   UserMapper mapper = session.getMapper(UserMapper.class);
   UserMapper mapper2 =session2.getMapper(UserMapper.class);

   User user = mapper.queryUserById(1);
   System.out.println(user);
   session.close();

   User user2 = mapper2.queryUserById(1);
   System.out.println(user2);
   System.out.println(user==user2);

   session2.close();
}
```

> 结论

- 只要开启了二级缓存，我们在同一个Mapper中的查询，可以在二级缓存中拿到数据
- 查出的数据都会被默认先放在一级缓存中
- 只有会话提交或者关闭以后，一级缓存中的数据才会转到二级缓存中

## 10.4缓存原理

![image-20210203164059245](C:\Users\刘凯丽\AppData\Roaming\Typora\typora-user-images\image-20210203164059245.png)



## 10.5 自定义缓存ehcache

1.导入包

2.在配置文件中配置

3.配置文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
        updateCheck="false">
   <!--
      diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下：
      user.home – 用户主目录
      user.dir – 用户当前工作目录
      java.io.tmpdir – 默认临时文件路径
    -->
   <diskStore path="./tmpdir/Tmp_EhCache"/>
   
   <defaultCache
           eternal="false"
           maxElementsInMemory="10000"
           overflowToDisk="false"
           diskPersistent="false"
           timeToIdleSeconds="1800"
           timeToLiveSeconds="259200"
           memoryStoreEvictionPolicy="LRU"/>

   <cache
           name="cloud_user"
           eternal="false"
           maxElementsInMemory="5000"
           overflowToDisk="false"
           diskPersistent="false"
           timeToIdleSeconds="1800"
           timeToLiveSeconds="1800"
           memoryStoreEvictionPolicy="LRU"/>
   <!--
      defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
    -->
   <!--
     name:缓存名称。
     maxElementsInMemory:缓存最大数目
     maxElementsOnDisk：硬盘最大缓存个数。
     eternal:对象是否永久有效，一但设置了，timeout将不起作用。
     overflowToDisk:是否保存到磁盘，当系统当机时
     timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
     timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
     diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
     diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
     diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
     memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
     clearOnFlush：内存数量最大时是否清除。
     memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
     FIFO，first in first out，这个是大家最熟的，先进先出。
     LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
     LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
  -->

</ehcache>
~~~

