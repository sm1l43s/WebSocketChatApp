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
    <c:url value="/edit_post" var="var"/>
    <form action="${var}" method="POST">
        <p class="registration edit_post">
            <label>Заголовок статьи:</label>
            <input type="text" name="title" id="name" value="${post.title}">
        </p>

        <p class="registration">
            <label>Описание поста:</label>
            <textarea name="description">${post.description}</textarea>
        </p>
        <input type="hidden" name="id" value="${post.id}">
        <input type="submit" value="Сохранить изменения">
        <a href="/chatApp/news" class="back">Вернуться назад</a>
    </form>
</div>
</body>
</html>

