<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Новости</title>
    <link rel="shortcut icon" href="<c:url value="/res/img/icon.png"/>" type="image/png">
    <link rel="stylesheet"  href="<c:url value="/res/css/style.css"/>">
    <link href="<c:url value="/res/fontawesome-free-5.13.0-web/css/all.min.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="wrapper">

    <c:import url="parts/profile.jsp"/>

    <c:import url="parts/header.jsp"/>

    <main>
        <div class="wrapperPost">
            <div class="postWrapper">
                <div class="posts">
                    <c:forEach var="post" items="${postList}">
                        <div class="post">
                            <h3 class="title_post">${post.title}</h3>
                            <span class="description_post">${post.description}</span>
                            <span class="sender_post"><i class="far fa-share-square"></i>
                                    ${post.user.name}
                            </span>

                            <c:if test="${user.role == 'ADMIN'}">
                                <span class="action_post">
                                    <a href="/chatApp/edit_post/${post.id}">
                                    <i class="far fa-edit"></i> Редактировать пост
                                </a>

                                <a href="/chatApp/delete_post/${post.id}">
                                    <i class="fas fa-trash-alt"></i> Удалить пост
                                </a>
                                </span>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

                <div class="page_count">
                    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                        <div class="page">
                            <c:url value="/news" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <a href="${url}">${i.index}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <c:if test="${user.role == 'ADMIN'}">
            <div class="addPost">
                <form action="/chatApp/newsAdd" method="post">
                    <fieldset class="clearfix">
                        <label>Заголовок поста:
                            <input type="text" name="title" placeholder="О чудные мгновения...">
                        </label>

                        <label>Содержание поста:
                            <textarea name="description" placeholder="С учётом сложившейся международной обстановки..."></textarea>
                        </label>
                        <span>
                            <input type="submit" id="add_post" value="Добавить">
                            <input type="reset" id="reset" value="Сбросить">
                        </span>
                    </fieldset>
                </form>

            </div>
            </c:if>
        </div>


    </main>
</div>

<c:import url="parts/footer.jsp"/>

</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="<c:url value="/res/js/chat.js"/>"></script>
<script src="<c:url value="/res/js/userOnline.js"/>"></script>
<script src="<c:url value="/res/js/sidePanel.js"/>"></script>

</html>

