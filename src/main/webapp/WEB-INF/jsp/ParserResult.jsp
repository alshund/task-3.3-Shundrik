<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>Parse Result</title>
</head>
<body>
    <c:choose>
        <c:when test="${requestScope.studentList.size() != 0}">

            <table border="5">
                <c:forEach items="${requestScope.page}" var="student">
                    <tr>
                        <td><c:out value="${student.surname}"/></td>
                        <td><c:out value="${student.firstName}"/></td>
                        <td><c:out value="${student.patronymic}"/></td>
                        <td><c:out value="${student.brothersAmount}"/></td>
                        <td><c:out value="${student.sistersAmount}"/></td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>

        <c:when test="${requestScope.studentList.size() == 0 }">

            <c:out value="The parse has not given any result!"/>

        </c:when>

    </c:choose>

</body>
</html>
