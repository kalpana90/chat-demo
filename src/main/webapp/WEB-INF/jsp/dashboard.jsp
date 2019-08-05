<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <script src="<c:url value="/js/socket.js" />"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.4/handlebars.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/style.css" />"/>
</head>
<body>

<input type="hidden" id="sessionId" value="${sessionId}"/>
<input type="hidden" id="path"  value="${socketUrl}"/>

<div class="container bg-w">
    <span id="demo"></span>
    <span id="scrollDown"></span>
    <h1 class="title">Participants</h1>
    <c:forEach items="${users}" var="users">
    <form action="${pageContext.request.contextPath}/messages" method="post" modelAttribute="messageRequestDTO">

        <a href="#" class="chat-row">
            <div class="letter">m</div>
            <p class="name">${users.name}</p>
            <button type="submit" class="btn-hidden"></button>
        </a>

        <input type="hidden" id="userId" name="userId" value="${userId}"/>
        <input type="hidden" id="senderId" name="senderId" value="${users.id}"/>
    </form>
    </c:forEach>
    <div id="socketmessages"></div>
</div>
</body>
</html>
