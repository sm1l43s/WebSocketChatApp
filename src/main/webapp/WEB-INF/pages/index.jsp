<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Веб приложение "Чат"</title>
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

<div class="wrapper">
    <div class="side-panel">
        <div class="userInfoItems">
            <h2>Информация о профиле</h2>
        </div>

        <div class="userInfoItems">
            <span>Логин: </span>
            <span>${user.name}</span>
        </div>

        <div class="userInfoItems">
            <span>Роль: </span>
            <span>${user.role.roleName}</span>
        </div>

        <div class="userInfoItems">
            <span>Статус: </span>
            <span>${user.status}</span>
        </div>

        <div class="userInfoItems">
            <span><a href="/edit/${user.id}">изменить данные профиля</a></span>
        </div>
    </div>

    <header>
        <div class="logo">
            <div><a href="/chat"><span>.Web</span>SocketChat</a></div>
        </div>

        <nav>
            <span><a href="/chat">Чат</a></span>
            <span><a href="#" class="sidePanel">Профиль</a></span>
            <span><a href="#">Контакты</a></span>
            <c:if test="${user.role.roleName.equals('admin')}">
                <span><a href="#">Админ панель</a></span>
            </c:if>

            <span>
                <c:url value="/logout" var="var"/>
                <form action="${var}" method="post" >
                    <input type="hidden" value="${user.id}">
                    <input id="logout" type="submit" value="Выйти"/>
                </form>
            </span>
        </nav>
    </header>

    <main>
        <div class="chatWrapper">
            <div class="chatBox">
                <div class="messages"></div>

                <div class="inputMsg">
                    <input type="text" class="msg">
                    <button>Отправить</button>
                </div>

                <div class="start">
                    <input type="hidden" class="username" value="${user.name}">
                    <button id="start">Присоединиться к чату</button>
                </div>
            </div>

        </div>

        <aside class="sidebar">
            <h3>В чате: <span id="countOnlineUsers"></span> </h3>
            <div class="listUsers">

            </div>
        </aside>

    </main>
</div>

<footer>
    <div>
        <span>Powered by Denis Brausov</span>
    </div>
</footer>


</body>

<script src="<c:url value="/res/js/chat.js"/>"></script>
<script src="<c:url value="/res/js/userOnline.js"/>"></script>
<script src="<c:url value="/res/js/sidePanel.js"/>"></script>

</html>
