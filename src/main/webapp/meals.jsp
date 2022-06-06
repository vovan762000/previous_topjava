<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%--        <jsp:useBean id="meals" class = "ru.javawebinar.topjava.model.MealTo" />--%>
    <c:forEach items="${meals}" var="meals" >
        <tr class=${meals.excess ? 'excess' : 'normal'}>
            <td><c:out value="${meals.dateTime}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>