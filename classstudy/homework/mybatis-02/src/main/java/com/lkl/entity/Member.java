package com.lkl.entity;

import java.util.Date;

/**
 * @author 刘凯丽
 * @createTime 2021/4/1 10:06
 * @projectName homework
 * @className Member.java
 * @description Member类
 */
public class Member {
    private String account;
    private String name;//姓名
    private String phone;//手机号
    private Date date;//注册时间

    public Member(){}
    public Member(String account, String name, String phone, Date date) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Member{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                '}';
    }
}
