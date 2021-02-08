package com.kai.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @createTime 2021/2/6 17:43
 * @projectName spring-study
 * @className User.java
 * @description TODO
 */

@Component
@Scope("prototype")
public class User {
    @Value("carry")
    public String name;
}
