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
