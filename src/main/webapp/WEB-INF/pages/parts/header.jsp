<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<header>
    <div class="logo">
        <div><a href="/chat"><span>.Web</span>SocketChat</a></div>
    </div>
    <nav>
        <span><a href="/chat">Чат</a></span>
        <span><a href="#" class="sidePanel">Профиль</a></span>
        <span><a href="#">Контакты</a></span>
        <c:if test="${user.role == 'ADMIN'}">
            <span><a href="/admin_panel">Админ панель</a></span>
        </c:if>
        <span>
            <a href="/customLogout" id="logout">Выйти</a>
        </span>
    </nav>
</header>