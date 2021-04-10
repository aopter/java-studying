<%@ page import="com.lkl.entity.Cake" %><%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<html>
<head>
    <title>蛋糕列表</title>
</head>
<body>
<a href="${ctx }">返回首页</a>
<%--循环输出蛋糕列表--%>
<h3>蛋糕列表</h3>
<%--搜索--%>
<form action='${ctx }/cake/items' method='post'>
    <input type='text' name='name' placeholder='请输入蛋糕名称'>
    <input type='submit' name='submit' value='搜索'>
</form>
<table border="1">
    <tr>
        <td>价格(元)</td>
        <td>重量(克)</td>
        <td>操作</td>
        <td>操作</td>

    </tr>
    <c:forEach var="cake" items="${page.list}">
        <tr>
            <td>${cake.price}</td>
            <td>${cake.weight}</td>

            <td><a href="${ctx }/cake/deleteCake?id=${cake.id}">删除</a></td>
            <td>
                <a href="${ctx }/cake/toUpdateCake?id=${cake.id}">更新</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
