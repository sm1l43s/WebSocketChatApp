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
    <input type="text" name="name" id="name" value="${user.name}">
    <label for="password">Password</label>
    <input type="text" name="password" id="password" value="${user.password}" >
    <input type="hidden" name="status" value="online">
    <input type="submit" value="Edit film">
</form>
</body>
</html>
