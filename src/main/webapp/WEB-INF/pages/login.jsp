<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log in</title>
    <link href="<c:url value="/res/css/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="login">
    <form action="" method="POST">
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span><input type="text" value="Логин" onBlur="if(this.value == '') this.value = 'Логин'" onFocus="if(this.value == 'Логин') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><span class="fontawesome-lock"></span><input type="password"  value="Пароль" onBlur="if(this.value == '') this.value = 'Пароль'" onFocus="if(this.value == 'Пароль') this.value = ''" required></p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><input type="submit" value="ВОЙТИ"></p>
        </fieldset>
    </form>
    <p>Нет аккаунта? &nbsp;&nbsp;<a href="#">Регистрация</a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
