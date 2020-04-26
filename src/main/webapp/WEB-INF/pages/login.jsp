<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log in</title>
    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="login">
    <div class="alert">
        <p>${alert}</p>
    </div>
    <c:url value="/login" var="var"/>
    <form action="${var}" method="POST">
        <fieldset class="clearfix">
            <input type="hidden" name="id" value="${user.id}">
            <p><span class="fontawesome-user"></span><input type="text" name="name" id="name" value="Логин" onBlur="if(this.value == '') this.value = 'Логин'" onFocus="if(this.value == 'Логин') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-lock"></span><input type="password" name="password" id="password"  value="Пароль" onBlur="if(this.value == '') this.value = 'Пароль'" onFocus="if(this.value == 'Пароль') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><input type="submit" value="ВОЙТИ"></p>
        </fieldset>
    </form>
    <p>Нет аккаунта? &nbsp;&nbsp;<a href="/add">Регистрация</a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
