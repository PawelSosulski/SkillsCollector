<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<h2>Login :in</h2>

<c:if test="${error!=null}">
    <span>${error}</span>
</c:if>
<form action="/login" method="post">
<fieldset>

    <div>
        <label for="login">Login</label>
        <input type="text" required  id="login" name="login"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" required  id="password" name="password"/>
    </div>
</fieldset>
<fieldset>
    <input type="submit" value="Login"/><input type="reset" value="Reset">
</fieldset>
</form>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>