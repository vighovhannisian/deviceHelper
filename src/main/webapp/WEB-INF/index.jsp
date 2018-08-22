<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vigen
  Date: 17.08.2018
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${allCategories}" var="categoryName">
    <a href="/getQuestionByCategory?category=${categoryName.name}">${categoryName.name}
  </a>
</c:forEach>
<c:forEach items="${allQuestions}" var="questionName">
  <a href="/getAnswerByQuestionId?id=${questionName.id}">${questionName.title}</a>
</c:forEach>

<a href="/sendSentence">Go to send sentence</a>
</body>
</html>
