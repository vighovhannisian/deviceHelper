<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vigen
  Date: 17.08.2018
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form action="/addQuestion" modelAttribute="addQuestion" enctype="multipart/form-data" method="post">
    <spring:input path="title"></spring:input>
    <spring:select path="categoryName">
        <c:forEach items="${allCategories}" var="allCategories">
            <option>${allCategories.name}</option>
        </c:forEach>
            </spring:select>
    <spring:textarea path="text"></spring:textarea>
    <%--<spring:input type="file" name="pic" path="image"></spring:input>--%>
    <input type="file" name="pic">
    <input type="submit" value="Add">
</spring:form>
</body>
</html>
