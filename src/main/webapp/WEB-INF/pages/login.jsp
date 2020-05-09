<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log in</title>
    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/res/fontawesome-free-5.13.0-web/css/all.min.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="login">
    <c:if test="${error}">
        <div class="alert">
            Неверный логин, пароль или Вас забанили.
        </div>
    </c:if>

    <c:if test="${logout}">
        <div class="successful">
            Выход из системы выполнен успешно.
        </div>
    </c:if>

    <form action="/login" method="post">
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"><i class="fas fa-user"></i></span><input type="email" name="email" id="name" placeholder="Электронная почта" onBlur="if(this.value == '') this.value = 'Логин'" onFocus="if(this.value == 'Логин') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-lock"><i class="fas fa-lock"></i></span><input type="password" name="password" id="password"  placeholder="Пароль" onBlur="if(this.value == '') this.value = 'Пароль'" onFocus="if(this.value == 'Пароль') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><input type="submit" value="ВОЙТИ"></p>
        </fieldset>
    </form>
    <p>Нет аккаунта? &nbsp;<i class="fas fa-arrow-right"></i>&nbsp;&nbsp;<a href="/add">Регистрация</a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
