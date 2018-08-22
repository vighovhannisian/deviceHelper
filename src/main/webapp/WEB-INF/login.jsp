<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vigen
  Date: 17.08.2018
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/loginPage"/>" method="post" name="loginForm">
    <input  type="text" placeholder="email" name="j_email"/>
    <input type="password" placeholder="password" name="j_password"/>
    <button type="submit" class="login100-form-btn">Login</button>
</form>
</body>
</html>
