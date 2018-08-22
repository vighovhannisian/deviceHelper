<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vigen
  Date: 21.08.2018
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form action="/sendSentences" modelAttribute="sentence" method="post">
    <spring:input path="userUsername"  required="please use this" type="email"></spring:input>
    <spring:textarea path="sentence"  required="please use this"></spring:textarea>
   <input type="submit" value="send" >
</spring:form>

<script>
    function myFunction() {
        alert("Thank You Your Sentence Successfully sended ");
    }
</script>
</body>
</html>
