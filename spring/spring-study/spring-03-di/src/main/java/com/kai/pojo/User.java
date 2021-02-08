package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘凯丽
 * @createTime 2021/2/5 18:11
 * @projectName spring-study
 * @className User.java
 * @description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;

}
