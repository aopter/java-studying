package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 16:47
 * @projectName spring-study
 * @className People.java
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @Resource
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;

}
