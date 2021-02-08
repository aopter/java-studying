package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:06
 * @projectName mybatis-study
 * @className Student.java
 * @description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;

    private int tid;
}
