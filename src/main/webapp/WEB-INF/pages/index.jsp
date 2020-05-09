<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Веб приложение "Чат"</title>
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
    <link href="<c:url value="/res/fontawesome-free-5.13.0-web/css/all.min.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="wrapper">

    <c:import url="parts/profile.jsp"/>

    <c:import url="parts/header.jsp"/>

    <main>
        <div class="chatWrapper">
            <div class="chatBox">
                <div class="messages">
                </div>
                <div class="inputMsg">
                    <input type="text" class="msg">
                    <button>Отправить</button>
                </div>

                <div class="start">
                    <input type="hidden" class="username_chat" value="${user.name}">
                    <button id="start">Присоединиться к чату</button>
                </div>
            </div>
            <input type="hidden" id="role_user" value="${user.role}">
        </div>

        <aside class="sidebar">
            <h3>В чате: <span id="countOnlineUsers"></span> </h3>
            <div class="listUsers">

            </div>
        </aside>

    </main>
</div>

<c:import url="parts/footer.jsp"/>

</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="<c:url value="/res/js/chat.js"/>"></script>
<script src="<c:url value="/res/js/userOnline.js"/>"></script>
<script src="<c:url value="/res/js/sidePanel.js"/>"></script>
</html>
