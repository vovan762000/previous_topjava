<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add meal</title>
  <style>
    label{
      display: inline-block;
      float: left;
      clear: left;
      width: 100px;
      text-align: left;
    }
    input {
      display: inline-block;
      float: left;
    }
    .btn:last-child{
      margin-left: 10px;
    }
  </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Edit Meal</h1>
<form method="POST" action='meals' name="meal">
  <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo" scope="session"/>
  <input type="hidden" readonly="readonly" name="id" value="<c:out value="${meal.id}" />"/>
  <label>DateTime:</label> <input
        type="datetime-local" name="dateTime"
        value="<c:out value="${meal.dateTime}" />"/></br></br>
  <label>Description:</label> <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/></br></br>
    <label>Calories: </label><input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/></br></br>
    <input type="submit" value="Save"/>
  <input class="btn" type="button"  value="Cancel" onCLick="history.back()">
</form>
</body>
</html>
