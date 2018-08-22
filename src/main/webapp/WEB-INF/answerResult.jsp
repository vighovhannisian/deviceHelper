<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vigen
  Date: 17.08.2018
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${findAllAnswersByQuestionId}" var="name">
    ${name.text}
<div style="width: 150px;background: url(../noPhotoPicture/noPhoto1.jpg)")><img src="/question/image?fileName=${name.image}" style="width: 150px" height="150"></img></div>
</c:forEach>
<spring:form action="/addAnswer" method="post" enctype="multipart/form-data" modelAttribute="addAnswer">
    <input type="hidden" value="${idAnswer.id}" name="questionId">
    <spring:textarea path="text" required="please use this" placholder="Your Answer"></spring:textarea>
    <input type="file" name="pict">
    <input type="submit" value="add">
</spring:form>

</body>
</html>
