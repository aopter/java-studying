<%--
  Created by IntelliJ IDEA.
  User: 刘凯丽
  Date: 2020/10/29
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<html>
<head>
    <title>添加蛋糕</title>
</head>
<body>
<%
    if (null == request.getAttribute("types")) {
        response.sendRedirect("toAddCake");
        return;
    }
%>
<a href="${ctx }">返回首页</a>
<h1>添加新品蛋糕</h1>
<form action="addCake" method="post" enctype="multipart/form-data">
    蛋糕价格(元) : <input type="text" name="cakePrice"/><br/>
    蛋糕重量(克) : <input type="text" name="cakeWeight"/><br/>
    蛋糕图片:<input type="file" name="upload"><br/>
    <input type="submit" value="添加蛋糕"><br/>
</form>

</body>
</html>
