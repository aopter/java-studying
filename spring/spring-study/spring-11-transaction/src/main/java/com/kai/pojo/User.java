package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 17:59
 * @projectName spring-study
 * @className User.java
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;

}
