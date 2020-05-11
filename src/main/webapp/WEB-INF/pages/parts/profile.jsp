<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<div class="side-panel">
    <div class="userInfoItems">
        <h2>Информация о профиле</h2>
        <input type="hidden" class="user_id" value="${user.id}">
    </div>

    <div class="userInfoItems">
        <span>Е-mail: </span>
        <span>${user.email}</span>
    </div>

    <div class="userInfoItems">
        <span>Логин: </span>
        <span>${user.name}</span>
    </div>

    <div class="userInfoItems">
        <span>Роль: </span>
        <span>${user.role}</span>
    </div>

    <div class="userInfoItems">
        <span>Статус: </span>
        <span>${user.status}</span>
    </div>

    <div class="userInfoItems">
        <span><a href="/chatApp/edit/${user.id}">изменить данные профиля</a></span>
    </div>
</div>
