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
                <tr>
                    <td>Фамилия</td>
                    <td>Имя</td>
                    <td>Отчество</td>
                    <td>Количество братьев</td>
                    <td>Количество сестер</td>
                    <td>Фамилия отца</td>
                    <td>Имя отца</td>
                    <td>Отчество отца</td>
                    <td>Заработная плата отца</td>
                    <td>Фамилия матери</td>
                    <td>Имя матери</td>
                    <td>Отчество матери</td>
                    <td>Заработная плата матери</td>
                </tr>
                
                <c:forEach items="${requestScope.page}" var="student">
                    <tr>
                        <td><c:out value="${student.surname}"/></td>
                        <td><c:out value="${student.firstName}"/></td>
                        <td><c:out value="${student.patronymic}"/></td>
                        <td><c:out value="${student.brothersAmount}"/></td>
                        <td><c:out value="${student.sistersAmount}"/></td>
                        <td><c:out value="${student.father.surname}"/></td>
                        <td><c:out value="${student.father.firstName}"/></td>
                        <td><c:out value="${student.father.patronymic}"/></td>
                        <td><c:out value="${student.father.salary}"/></td>
                        <td><c:out value="${student.mother.surname}"/></td>
                        <td><c:out value="${student.mother.firstName}"/></td>
                        <td><c:out value="${student.mother.patronymic}"/></td>
                        <td><c:out value="${student.mother.salary}"/></td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>

        <c:when test="${requestScope.studentList.size() == 0 }">

            <c:out value="The parse has not given any result!"/>

        </c:when>

    </c:choose>
    <p>
        <form action="FrontController" method="get">
            <input type="hidden" name="command" value="previous_page">
            <input type="submit" value="PREVIOUS PAGE">
        </form>

        <form action="FrontController" method="get">
            <input type="hidden" name="command" value="next_page">
            <input type="submit" value="NEXT PAGE">
        </form>
    </p>

</body>
</html>
