<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактиование профиля пользователя</title>
    <link rel="shortcut icon" href="<c:url value="/res/img/icon.png"/>" type="image/png">
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
</head>
<body>
<div id="login">
    <c:url value="/edit_admin" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="email" value="${user.email}">
        <p class="registration">
            <label>Логин:</label>
            <input type="text" name="name" id="name" value="${user.name}">
        </p>

        <input type="hidden" name="status" value="${user.status}">

        <p class="registration">
            <label>Роль:</label>
            <select name="role">
                <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if> >Админ</option>
                <option value="USER" <c:if test="${user.role == 'USER'}">selected</c:if> >Пользователь</option>
                <option value="MODERATOR" <c:if test="${user.role == 'MODERATOR'}">selected</c:if> >Модератор</option>
            </select>
        </p>

        <p class="registration">
            <label>Статус:</label>
            <select name="blocked">
                <option value="true" <c:if test="${user.blocked == 'true'}">selected</c:if> >Забанен</option>
                <option value="false" <c:if test="${user.blocked == 'false'}">selected</c:if> >Не забанен</option>
            </select>
        </p>

        <input type="hidden" name="password" value="${user.password}">
        <input type="submit" value="Сохранить изменения">
        <a href="/chatApp/admin_panel" class="back">Вернуться назад</a>
    </form>
</div>
</body>
</html>

