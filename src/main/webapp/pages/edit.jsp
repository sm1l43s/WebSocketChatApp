<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/edit" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${user.id}">
    <label for="name">Name</label>
    <input type="text" name="name_user" id="name">
    <label for="password">Password</label>
    <input type="password" name="password_user" id="password">
    <input type="submit" value="Edit film">
</form>
</body>
</html>
