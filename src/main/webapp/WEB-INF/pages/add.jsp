<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user</title>
    <link rel="shortcut icon" href="<c:url value="/res/img/icon.png"/>" type="image/png">
    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>

<div id="login">
    <div class="alert">
        <p></p>
    </div>
    <c:url value="/add" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="id" value="${user.id}">
        <p class="registration">
            <label>Введите адрес электронной почты:</label>
            <input type="email" class="login" name="email" id="email" placeholder="Электронная почта">
        </p>

        <p class="registration">
            <label>Введите логин:</label>
            <input type="text" name="name" id="name" placeholder="Логин">
        </p>

        <p class="registration">
            <label>Введите пароль:</label>
            <input type="password" name="password" id="password" placeholder="Пароль">
        </p>

        <p class="registration">
            <label>Повторите пароль:</label>
            <input type="password" id="password2" placeholder="Пароль">
        </p>
        <input type="hidden" name="status" value="offline">
        <p><input type="submit" value="Зарегистрироваться" id="btn_submit"></p>
    </form>
</div>

</body>
<script src="<c:url value="/res/js/registration.js"/>"></script>
</html>
