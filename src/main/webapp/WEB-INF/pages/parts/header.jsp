<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="logo">
        <div><a href="/chatApp/chat"><span>.Web</span>SocketChat</a></div>
    </div>
    <nav>
        <span><a href="/chatApp/chat">Чат</a></span>
        <span><a href="#" class="sidePanel">Профиль</a></span>
        <span><a href="#">Контакты</a></span>
        <c:if test="${user.role == 'ADMIN'}">
            <span><a href="/chatApp/admin_panel">Админ панель</a></span>
        </c:if>
        <span>
            <a href="/chatApp/customLogout" id="logout">Выйти</a>
        </span>
    </nav>
</header>