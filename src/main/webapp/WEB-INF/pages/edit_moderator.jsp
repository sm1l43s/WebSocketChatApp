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
    <c:url value="/edit_moderator" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="email" value="${user.email}">
        <input type="hidden" name="name" id="name" value="${user.name}">
        <input type="hidden" name="status" value="${user.status}">
        <input type="hidden" name="role" value="${user.role}">

<%--        <c:if test="${user.role == 'USER'}">--%>
        <p class="registration">
            <label>Статус:</label>
            <select name="blocked">
                <option value="true" <c:if test="${user.blocked == 'true'}">selected</c:if> >Забанен</option>
                <option value="false" <c:if test="${user.blocked == 'false'}">selected</c:if> >Не забанен</option>
            </select>
        </p>
<%--        </c:if>--%>

        <input type="hidden" name="password" value="${user.password}">
        <input type="submit" value="Сохранить изменения">
        <a href="/chatApp/chat" class="back">Вернуться назад</a>
    </form>
</div>
</body>
</html>


