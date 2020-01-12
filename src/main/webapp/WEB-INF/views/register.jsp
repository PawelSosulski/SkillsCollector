<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<h2>Rejestracja</h2>
<c:if test="${error != null}">
    <div>${error}</div>
</c:if>
<form action="/register" method="post">
<fieldset>
    <legend>Registration date</legend>
    <div>
        <label for="login">Login</label>
        <input type="text" required  id="login" name="login" value="${newUser.username}"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" required  id="password" name="password" value="${newUser.password}"/>
    </div>
</fieldset>
<fieldset>
    <legend>Personal data</legend>
    <div>
        <label for="firstName">First Name</label>
        <input type ="text" required id="firstName" name="firstName" value="${newUser.firstName}"/>
    </div>
    <div>
        <label for="lastName">Last Name</label>
        <input type="text" required id="lastName" name="lastName"value="${newUser.lastName}"/>
    </div>
</fieldset>
<fieldset>
    <input type="submit" value="Save"/><input type="reset" value="Reset">
</fieldset>
</form>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>