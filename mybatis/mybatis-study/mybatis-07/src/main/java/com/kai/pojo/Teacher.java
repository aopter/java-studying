package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:07
 * @projectName mybatis-study
 * @className Teacher.java
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private int id;
    private String name;

    private List<Student> students;
}
