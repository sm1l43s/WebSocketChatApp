<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<c:url value="/add" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${user.id}">
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="password">Password</label>
    <input type="password" name="password" id="password">
    <input type="hidden" name="status" value="offline">
    <input type="submit" value="Add new user">
</form>
</body>
</html>
