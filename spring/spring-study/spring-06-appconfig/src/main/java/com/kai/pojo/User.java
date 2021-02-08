package com.kai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 18:03
 * @projectName spring-study
 * @className User.java
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class User {
    @Value("carry")
    private String name;
}
