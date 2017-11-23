<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Parse Result</title>
</head>
<body>
    <c:set var="studentList" scope="request" value="${requestScope.ParserResult}"/>
    <c:if test="${not empty studentList}"/>
    
</body>
</html>
