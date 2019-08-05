
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <script src="<c:url value="/js/socket.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/style.css" />"/>
</head>
<body>

<input type="hidden" id="sessionId" value="${sessionId}"/>
<input type="hidden" id="path"  value="${socketUrl}"/>


<div class="container bg-w">
    <span id="demo"></span>
    <form action="${pageContext.request.contextPath}/dashboard" method="post" modelAttribute="userId">
        <input type="hidden" name="userId" value="${userId}"/>
        <div class="title">
            <button class="back">&lsaquo;</button>
            <c:set var = "string1" value ='${userName}' />
            <c:set var = "string2" value = "${fn:substring(string1,0,1)}" />
            <h2 class="letter">${string2}</h2>${userName}
        </div>
    </form>

    <div class="chat-view" id="scrollDown">
        <c:forEach items="${userMessages}" var="userMessages">
            <c:choose>
                <c:when test="${userMessages.user.id ==userId}">
                    <div class="chat-receive">
                        <p id="msg-receive" class="msg">${userMessages.message}</p>
                        <fmt:formatDate value="${userMessages.createdDate}" pattern="yyyy.MM.dd hh:mm:ss a" var="senderTime"/>
                        <p id="time-receive" class="date"><c:out value="${senderTime}" /></p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="chat-send">
                        <p id="msg-send" class="msg">${userMessages.message}</p>
                        <fmt:formatDate value="${userMessages.createdDate}" pattern="yyyy.MM.dd hh:mm:ss a" var="userTime"/>
                        <p id="time-send" class="date"><c:out value="${userTime}" /></p>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <div id="socketmessages"></div>
    </div>

    <form action="${pageContext.request.contextPath}/send/message" method="post" modelAttribute="sendMessageRequestDTO">
        <input type="hidden" id="userId" name="userId" value="${userId}"/>
        <input type="hidden" id="senderId" name="senderId" value="${senderId}"/>
        <div class="chat-msg">
            <input class="chat-msg-input" id="msg-type" name="message" type="text" placeholder="Message..."/>
            <input class="chat-msg-btn" id="btn-send" type="submit" value="send"/>
        </div>
    </form>

    <form id="socketForm" style="display: none" action="${pageContext.request.contextPath}/messages" method="post" modelAttribute="messageRequestDTO">
        <input type="hidden" id="userId1" name="userId" value="${userId}"/>
        <input type="hidden" id="senderId1" name="senderId" value="${users.id}"/>
    </form>

</div>

<script>
    window.scrollTo(0,document.body.scrollHeight);
</script>

</body>
</html>
