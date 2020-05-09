<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактиование профиля</title>
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
</head>
<body>
<div id="login">
    <c:url value="/edit" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="email" value="${user.email}">

        <p class="registration">
            <label>Введите логин:</label>
            <input type="text" name="name" id="name" value="${user.name}">
        </p>

        <input type="hidden" name="role" value="${user.role}">
        <input type="hidden" name="status" value="${user.status}">
        <input type="hidden" name="password" value="${user.password}">
        <input type="hidden" name="blocked" value="${user.blocked}">
        <input type="submit" value="Сохранить изменения">
        <a href="/chat" class="back">Вернуться назад</a>
    </form>
</div>
</body>
</html>
