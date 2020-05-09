<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Админ панель</title>
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
    <link href="<c:url value="/res/fontawesome-free-5.13.0-web/css/all.min.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <c:import url="parts/profile.jsp"/>

    <c:import url="parts/header.jsp"/>

    <main>
        <div class="table-wrapper">
            <div class="filter">
                <div class="search">
                    <input type="text" class="inp_search" placeholder="Поиск по логину или e-mail">
                    <a href="/admin_panel?search=" id="searchBtn"><i class="fas fa-search"></i></a>
                </div>
                <div class="dropdown">
                    <button onclick="myFunction()" class="dropbtn">Фильтр <i class="fas fa-filter"></i></button>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="/admin_panel">Без фильтра</a>
                        <span>По роли:</span>
                        <a href="/admin_panel?filter=ADMIN">Администратор</a>
                        <a href="/admin_panel?filter=MODERATOR">Модератор</a>
                        <a href="/admin_panel?filter=USER">Пользователь</a>
                        <span>По блокировки аккаунта</span>
                        <a href="/admin_panel?filter=true">Заблокирован</a>
                        <a href="/admin_panel?filter=false">Разблокирован</a>
                        <span>По активности:</span>
                        <a href="/admin_panel?filter=online">Онлайн</a>
                        <a href="/admin_panel?filter=offline">Оффлайн</a>
                    </div>
                </div>
            </div>

            <table class="fl-table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>E-mail</th>
                    <th>Login</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>is blocked</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.role}</td>
                        <td>${user.status}</td>
                        <td>${user.blocked}</td>
                        <td>
                            <a href="/edit_admin/${user.id}" class="edit"><i class="fas fa-user-edit"></i></a>
                            <a href="/delete_admin/${user.id}" class="remove"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                    </c:forEach>
                <tbody>
            </table>

            <div class="page_count">
                <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                    <div class="page">
                    <c:url value="/admin_panel" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href="${url}">${i.index}</a>
                    </div>
                </c:forEach>
            </div>
        </div>

    </main>

</div>

<c:import url="parts/footer.jsp"/>

</body>
<script src="<c:url value="/res/js/sidePanel.js"/>"></script>
<script src="<c:url value="/res/js/adminPanel.js"/>"></script>
</html>
