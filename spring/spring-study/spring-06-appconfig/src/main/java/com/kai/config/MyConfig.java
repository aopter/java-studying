package com.kai.config;

import com.kai.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 18:04
 * @projectName spring-study
 * @className MyConfig.java
 * @description TODO
 */
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
