<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Meals</h1>
<table border="1" cellpadding="5">
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${meals}" var="meals">
        <jsp:useBean id="meals" class="ru.javawebinar.topjava.model.MealTo" scope="session"/>
        <tr class=${meals.excess ? 'excess' : 'normal'}>
            <fmt:parseDate value="${meals.dateTime}" pattern="yyyy-MM-dd'T'mm:ss" var="dateTime" type="both"/>
            <td><fmt:formatDate pattern="yyyy-MM-dd mm:ss" value="${dateTime}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>