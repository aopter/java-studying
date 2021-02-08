package com.kai.util;

import org.junit.Test;
import java.util.UUID;

/**
 * @author 刘凯丽
 * @createTime 2021/2/2 15:51
 * @projectName mybatis-study
 * @className IDutils.java
 * @description TODO
 */
@SuppressWarnings("all")//抑制警告
public class IDutils {

    public static String getID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    @Test
    public void test(){

        System.out.println(getID());
        System.out.println(getID());
        System.out.println(getID());
        System.out.println(getID());
    }
}
