package com.lkl.dao;

import com.lkl.entity.Member;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 刘凯丽
 * @createTime 2021/4/1 10:06
 * @projectName homework
 * @className MemerMApper.java
 * @description 会员操作的mapper类
 */
public interface MemberMapper {
//    1、  给一个新用户开卡，操作成功后在控制台打印“添加成功”。
    int addMember(Member member);
//    2、  查询今年2月份新增的且会员姓名包含“张”的会员信息，并显示到控制台。
    List<Member> searchMember();
//    3、  查询所有会员数据，并显示到控制台。
    List<Member> selectAllMember();
//    4、  根据会员账号修改会员手机号，操作成功时在控制台打印“更新成功”。
    int updatePhoneByAccount(Map<String,Object> map);
//    5、  根据会员账号注销某会员信息，操作成功时在控制台打印“注销成功”
    int deleteMember(String account);
}
