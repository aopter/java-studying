package com.lkl.dao;

import com.lkl.entity.Member;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/4/2 19:39
 * @projectName homework
 * @className MemberTypeDao.java
 * @description TODO
 */
public interface MemberTypeMapper {

//1、 批量添加五种会员类型，操作成功在控制台打印”批量添加成功”。
    int addTypes(List<Member> members);
//
//2、  已知会员账号的值，为该会员分配一种类型。
//
//3、  已知会员类型id的值，查询该会员类型的类型信息及所有会员信息。
//
//4、 已知会员账号，查询该会员信息及所属类型信息。
//
//5、  查询会员数量最多的两种类型。
//
//6、 当会员等级升级时，完成将会员关联的类型修改成更高类型的操作。
//
//7、  已知会员类型id的值，删除属于该类型的包含某个关键字的会员信息。
}
