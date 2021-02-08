package com.kai.demo02;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:18
 * @projectName spring-study
 * @className UserServiceProxy.java
 * @description TODO
 */
public class UserServiceProxy implements UserService{
    private UserServiceImpl userServiceImpl;

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public void add() {
        log("add");
        userServiceImpl.add();
    }

    public void delete() {
        log("delete");
        userServiceImpl.delete();
    }

    public void update() {
        log("update");
        userServiceImpl.update();
    }

    public void query() {
        log("query");
        userServiceImpl.query();
    }

//    日志方法
    private void log(String msg){
        System.out.println("调用了"+msg+"方法");
    }
}
