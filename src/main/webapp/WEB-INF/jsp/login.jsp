<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css" />"/>
</head>
<body>

<form class="form-horizontal"
      action="${pageContext.request.contextPath}/user/login" method="post" modelAttribute="user">
    <div class="container">
        <c:if test="${error}">
            <h1 class="error">${errorMessage}</h1>
        </c:if>
        <form id="form_id" method="post" name="myform">
            <input type="text" name="userName" id="userName" class="v-center" placeholder="Username"/>
            <input type="password" name="password" id="password" placeholder="Password"/>
            <input class="button" type="submit" value="Login" id="submit"/>
        </form>
    </div>
</form>
</body>
</html>
